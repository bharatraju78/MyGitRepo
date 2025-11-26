package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.vam.cco.dao.entity.Labels;
import com.vam.cco.services.LabelsService;

@Controller
@RequestMapping("/admin/labels")
public class LabelsController {
    private static final Logger logger = LoggerFactory.getLogger(LabelsController.class);
    private final LabelsService labelsService;

    @Autowired
    public LabelsController(LabelsService labelsService) {
        this.labelsService = labelsService;
    }

    @GetMapping("/labels-list")
    public String listLabels(Model model,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false, name = "successMessage") String successMessage,
                            @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        logger.info("Fetching labels with pagination. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Labels> labelsPage = labelsService.findAll(pageable);
        model.addAttribute("labels", labelsPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", labelsPage.getTotalPages());
        if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
        if (errorMessage != null) {
        	model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("Total labels found: {}", labelsPage.getTotalElements());
        return "users/labelsList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add label form");
        Labels labels = new Labels();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			labels.setOrderNo(labelsService.getMaxOrderNo());
			labels.setCreatedBy(authentication.getName());
			labels.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			labels.setModifiedBy(authentication.getName());
			labels.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
        model.addAttribute("labels", labels);
        return "users/labelsForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for label id: {}", id);
        Labels labels = labelsService.findById(id).orElseThrow(() -> new RuntimeException("Label not found"));
        model.addAttribute("labels", labels);
        return "users/labelsForm";
    }

    @PostMapping("/labels-save")
    public String saveLabels(@ModelAttribute Labels labels, Model model) {
        logger.info("Saving label: {}", labels.getLabelName());
        try {
        	Long id = labels.getLabelId();
			labelsService.save(labels);
			if (id == null) {
				model.addAttribute("successMessage", "Label created successfully");
			} else {
				model.addAttribute("successMessage", "Label updated successfully");
			}
		} catch (Exception e) {
			logger.error("Error saving label: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error saving label: " + e.getMessage());
		}
        logger.info("Label saved successfully");
        return "redirect:/admin/labels/labels-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteLabels(@PathVariable Long id) {
        logger.info("Deleting label with id: {}", id);
        labelsService.deleteById(id);
        logger.info("Label deleted successfully");
        return "redirect:/admin/labels/labels-list";
    }
}