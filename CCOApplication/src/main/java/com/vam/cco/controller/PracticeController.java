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

import com.vam.cco.dao.entity.Practice;
import com.vam.cco.model.PracticeModel;
import com.vam.cco.services.PracticeService;
import com.vam.cco.util.StatusEnum;

@Controller
@RequestMapping("/admin/practice")
public class PracticeController {

    private static final Logger logger = LoggerFactory.getLogger(PracticeController.class);

    private final PracticeService practiceService;

    @Autowired
    public PracticeController(PracticeService practiceService) {
        this.practiceService = practiceService;
    }

    @GetMapping("/practice-list")
    public String listPractices(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false, name = "successMessage") String successMessage,
                                @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        logger.info("Fetching practices with pagination. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<PracticeModel> practicePage = practiceService.findAllPractices(pageable);
        model.addAttribute("practiceModels", practicePage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", practicePage.getTotalPages());
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("Total practices found: {}", practicePage.getTotalElements());
        return "users/practiceList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add practice form");
        PracticeModel practiceModel = new PracticeModel();
        practiceModel.setPracticeStatus(StatusEnum.ACTIVE.getStatus());
        practiceModel.setPracticeStatusList(StatusEnum.getAllStatus());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            practiceModel.setCreatedBy(authentication.getName());
            practiceModel.setModifiedBy(authentication.getName());
            practiceModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            practiceModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }
        model.addAttribute("practice", practiceModel);
        return "users/practiceForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for practice id: {}", id);
        Practice practice = practiceService.findById(id).orElseThrow(() -> new RuntimeException("Practice not found"));
        PracticeModel practiceModel = new PracticeModel();
        practiceModel.setPracticeId(practice.getPracticeId());
        practiceModel.setPracticeCode(practice.getPracticeCode());
        practiceModel.setPracticeName(practice.getPracticeName());
        practiceModel.setPracticeStatus(practice.getPracticeStatus());
        practiceModel.setCreatedBy(practice.getCreatedBy());
        if (practice.getCreatedDate() != null) {
            practiceModel.setCreatedDate(practice.getCreatedDate());
        } else {
            practiceModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (practiceModel.getCreatedBy() == null) {
                practiceModel.setCreatedBy(authentication.getName());
            }
            if (practiceModel.getModifiedBy() == null) {
                practiceModel.setModifiedBy(authentication.getName());
            }
        }
        if (practice.getModifiedDate() != null) {
            practiceModel.setModifiedDate(practice.getModifiedDate());
        } else {
            practiceModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }
        practiceModel.setPracticeStatusList(StatusEnum.getAllStatus());
        model.addAttribute("practice", practiceModel);
        return "users/practiceForm";
    }

    @PostMapping("/practice-save")
    public String savePractice(@ModelAttribute PracticeModel practiceModel, Model model) {
        logger.info("Saving practice: {}", practiceModel.getPracticeName());
        try {
            Practice practice = new Practice();
            Long practiceId = practiceModel.getPracticeId();
            practice.setPracticeId(practiceModel.getPracticeId());
            practice.setPracticeCode(practiceModel.getPracticeCode());
            practice.setPracticeName(practiceModel.getPracticeName());
            practice.setPracticeStatus(practiceModel.getPracticeStatus());
            practice.setCreatedBy(practiceModel.getCreatedBy());
            practice.setCreatedDate(practiceModel.getCreatedDate());
            practice.setModifiedBy(practiceModel.getModifiedBy());
            practice.setModifiedDate(practiceModel.getModifiedDate());
            practiceService.save(practice);
            if (practiceId == null) {
                model.addAttribute("successMessage", "Practice saved successfully");
            } else {
                model.addAttribute("successMessage", "Practice updated successfully");
            }
        } catch (Exception e) {
            logger.error("Error saving practice: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error creating Practice:" + e.getMessage());
        }
        return "redirect:/admin/practice/practice-list";
    }

    @GetMapping("/delete/{id}")
    public String deletePractice(@PathVariable Long id) {
        logger.info("Deleting practice with id: {}", id);
        practiceService.deleteById(id);
        logger.info("Practice deleted successfully");
        return "redirect:/admin/practice/practice-list";
    }
}
