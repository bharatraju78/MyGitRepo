package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vam.cco.dao.entity.Skill;
import com.vam.cco.model.SkillModel;
import com.vam.cco.services.SkillService;
import com.vam.cco.util.TechnologySolutionCenterEnum;

@Controller
@RequestMapping("/admin/skills")
public class SkillController {
	
	private final Logger logger = LoggerFactory.getLogger(SkillController.class);
	
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // --- JSP UI Endpoints ---
    @GetMapping("/list")
    public String listSkills(Model model, 
							 @RequestParam(required = false, name="successMessage") String successMessage,
							 @RequestParam(required = false, name="errorMessage") String errorMessage) {
        List<Skill> skills = skillService.findAll();
        model.addAttribute("skills", skills);
        if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
        if (errorMessage != null) {
        	model.addAttribute("errorMessage", errorMessage);
        }
        return "users/skillList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
    	SkillModel skill = new SkillModel();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 		if (authentication != null) {
 			skill.setCreatedBy(authentication.getName());
 			skill.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
 			skill.setModifiedBy(authentication.getName());
 			skill.setTechCenterList(TechnologySolutionCenterEnum.getAllTechCenters());
 			skill.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
 		}
        model.addAttribute("skill", skill);
        return "users/skillForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Skill> skill = skillService.findById(id);
        if (skill.isPresent()) {
        	Skill existingSkill = skill.get();
        	SkillModel skillModel = prepareModelForDisplay(existingSkill);
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	if (authentication != null) {
        		existingSkill.setModifiedBy(authentication.getName());
        		existingSkill.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        	}
            model.addAttribute("skill", skillModel);
            return "users/skillForm";
        } else {
            return "redirect:/admin/skills/list";
        }
    }

    

	@PostMapping("/skill-save")
    public String saveOrUpdate(@ModelAttribute SkillModel skill, Model model) {
        try {
			if (skill.getSkillId() == null) {
				Skill entity = prepareSkillForPersistence(skill);
				skillService.save(entity);
				model.addAttribute("successMessage", "Skill added successfully: ");
			} else {
				Skill entity = prepareSkillForPersistence(skill);
				skillService.update(entity);
				model.addAttribute("successMessage", "Skill updated successfully: ");
			}
		} catch (Exception e) {
			logger.error("Error saving skill: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error creating Skill: " + e.getMessage());
		}
        return "redirect:/admin/skills/list";
    }

    private Skill prepareSkillForPersistence(SkillModel skill) {
    	Skill entity = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(skill != null) {
			entity = skill.getSkillId() != null ? skillService.findById(skill.getSkillId()).orElse(new Skill()) : new Skill();
//			entity.setSkillId(skill.getSkillId());
			entity.setSkillName(skill.getSkillName());
			entity.setTechnologySolutionCenter(skill.getTechnologySolutionCenter());
			if(skill.getSkillId() == null) {
				entity.setCreatedBy(authentication.getName());
				entity.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}else {
				entity.setCreatedBy(skill.getCreatedBy());
				entity.setCreatedDate(skill.getCreatedDate());
			}
			entity.setModifiedBy(authentication.getName());
			entity.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			return entity;
		}
		return entity;
	}
    
    private SkillModel prepareModelForDisplay(Skill existingSkill) {
		SkillModel model = new SkillModel();
		if(existingSkill != null) {
			model.setSkillId(existingSkill.getSkillId());
			model.setSkillName(existingSkill.getSkillName());
			model.setTechnologySolutionCenter(existingSkill.getTechnologySolutionCenter());
			model.setCreatedBy(existingSkill.getCreatedBy());
			model.setCreatedDate(new Timestamp(existingSkill.getCreatedDate().getTime()));
			model.setModifiedBy(existingSkill.getModifiedBy());
			model.setModifiedDate(new Timestamp(existingSkill.getModifiedDate().getTime()));
			model.setTechCenterList(TechnologySolutionCenterEnum.getAllTechCenters());
			return model;
		}
		return model;
	}

	@GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        skillService.deleteById(id);
        return "redirect:/admin/skills/list";
    }

    // --- REST API Endpoints ---
    @RestController
    @RequestMapping("/api/skills")
    public static class SkillRestController {
        private final SkillService skillService;

        @Autowired
        public SkillRestController(SkillService skillService) {
            this.skillService = skillService;
        }

        @PostMapping
        public ResponseEntity<Skill> create(@RequestBody Skill skill) {
            return ResponseEntity.ok(skillService.save(skill));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Skill> getById(@PathVariable Long id) {
            return skillService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping
        public List<Skill> getAll() {
            return skillService.findAll();
        }

        @PutMapping("/{id}")
        public ResponseEntity<Skill> update(@PathVariable Long id, @RequestBody Skill skill) {
            skill.setSkillId(id);
            return ResponseEntity.ok(skillService.update(skill));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            skillService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}