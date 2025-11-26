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

import com.vam.cco.dao.entity.Vertical;
import com.vam.cco.services.VerticalService;

@Controller
@RequestMapping("/admin/verticals")
public class VerticalController {
	
	private final Logger logger = LoggerFactory.getLogger(VerticalController.class);
	
    private final VerticalService verticalService;

    @Autowired
    public VerticalController(VerticalService verticalService) {
        this.verticalService = verticalService;
    }

    @GetMapping("/list")
    public String listVerticals(Model model, @RequestParam(required = false, name="successMessage") String successMessage,
								@RequestParam(required = false, name="errorMessage") String errorMessage) {
        List<Vertical> verticals = verticalService.findAll();
        model.addAttribute("verticals", verticals);
        if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
        if (errorMessage != null) {
        	model.addAttribute("errorMessage", errorMessage);
        }
        return "users/verticalList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
    	Vertical vertical = new Vertical();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			vertical.setCreatedBy(authentication.getName());
			vertical.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			vertical.setModifiedBy(authentication.getName());
			vertical.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
        model.addAttribute("vertical", vertical);
        return "users/verticalForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Vertical> vertical = verticalService.findById(id);
        if (vertical.isPresent()) {
            model.addAttribute("vertical", vertical.get());
            return "users/verticalForm";
        } else {
            return "redirect:/admin/verticals/list";
        }
    }

    @PostMapping("/vertical-save")
    public String saveOrUpdate(@ModelAttribute Vertical vertical, Model model) {
    	logger.info("Saving or updating vertical: {}", vertical);
    	Long verticalId = vertical.getVerticalId();
        try {
			verticalService.save(vertical);
			if (verticalId == null || verticalId == 0) {
				model.addAttribute("successMessage", "Vertical created successfully ");
			} else {
				model.addAttribute("successMessage", "Vertical updated successfully");
			}
		} catch (Exception e) {
			logger.error("Error saving vertical: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error creating Vertical: " + e.getMessage());
		}
        logger.info("Vertical saved successfully ");
        return "redirect:/admin/verticals/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        verticalService.deleteById(id);
        return "redirect:/admin/verticals/list";
    }
    
}