package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vam.cco.dao.entity.Grade;
import com.vam.cco.services.GradeService;

@Controller
@RequestMapping("/admin/grades")
public class GradeController {
	
	private final Logger logger = LoggerFactory.getLogger(GradeController.class);
	
    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/list")
    public String listGrades(Model model, @RequestParam(required = false, name="successMessage") String successMessage,
							 @RequestParam(required = false, name="errorMessage") String errorMessage) {
        List<Grade> grades = gradeService.findAll();
        model.addAttribute("grades", grades);
        
        if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
        
        if (errorMessage != null) {
        	model.addAttribute("errorMessage", errorMessage);
        }
        return "users/gradeList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
    	Grade grade = new Grade();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 		if (authentication != null) {
 			grade.setCreatedBy(authentication.getName());
 			grade.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
 			grade.setModifiedBy(authentication.getName());
 			grade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
 		}
        model.addAttribute("grade", grade);
        return "users/gradeForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Grade> grade = gradeService.findById(id);
        if (grade.isPresent()) {
        	Grade existingGrade = grade.get();
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	if (authentication != null) {
        		existingGrade.setModifiedBy(authentication.getName());
        		existingGrade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        	}
            model.addAttribute("grade", existingGrade);
            return "users/gradeForm";
        } else {
            return "redirect:/admin/grades/list";
        }
    }

    @PostMapping("/grade-save")
    public String saveOrUpdate(@ModelAttribute Grade grade, Model model) {
    	logger.info("Saving or updating grade: {}", grade);
        try {
        	Long gradeId = grade.getGradeId();
			gradeService.save(grade);
			if (gradeId == null) {
				model.addAttribute("successMessage", "Grade created successfully!");
			} else {
				model.addAttribute("successMessage", "Grade updated successfully!");
			}
		} catch (Exception e) {
			logger.error("Error saving or updating grade: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error creating/updating Grade: " + e.getMessage());
		}
        return "redirect:/admin/grades/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        gradeService.deleteById(id);
        return "redirect:/admin/grades/list";
    }
    
}