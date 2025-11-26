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

import com.vam.cco.dao.entity.Designation;
import com.vam.cco.services.DesignationService;

@Controller
@RequestMapping("/admin/designations")
public class DesignationController {
    private final DesignationService designationService;
    private static final Logger logger = LoggerFactory.getLogger(DesignationController.class);

    @Autowired
    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    // --- JSP UI Endpoints ---
    @GetMapping("/list")
    public String listDesignations(Model model) {
        logger.info("Fetching all designations");
        List<Designation> designations = designationService.findAll();
        model.addAttribute("designations", designations);
        return "users/designationList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add designation form");
        Designation designation = new Designation();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			logger.info("AccountController::generateAccountNumber::Authentication found: {}", authentication.getName());
			designation.setCreatedBy(authentication.getName());
			designation.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			designation.setModifiedBy(authentication.getName());
			designation.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
        model.addAttribute("designation", designation);
        return "users/designationForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for designation with id: {}", id);
        Optional<Designation> designation = designationService.findById(id);
        if (designation.isPresent()) {
            model.addAttribute("designation", designation.get());
            return "users/designationForm";
        } else {
            logger.warn("Designation with id {} not found", id);
            return "redirect:/admin/designations/list";
        }
    }

    @PostMapping("/designation-save")
    public String saveOrUpdate(@ModelAttribute Designation designation, Model model) {
        logger.info("Saving or updating designation: {}", designation);
        try {
        	Long designationid = designation.getDesignationId();
			designationService.save(designation);
			if (designationid == null) {
				model.addAttribute("successMessage", "Designation added successfully ");
			} else {
				model.addAttribute("successMessage", "Designation updated successfully ");
			}
		} catch (Exception e) {
			logger.error("Error saving designation: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error saving designation: " + e.getMessage());
		}
        return "redirect:/admin/designations/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        logger.info("Deleting designation with id: {}", id);
        designationService.deleteById(id);
        return "redirect:/admin/designations/list";
    }

}