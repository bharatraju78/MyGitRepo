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

import com.vam.cco.dao.entity.WorkingHours;
import com.vam.cco.model.WorkingHoursModel;
import com.vam.cco.services.WorkingHoursService;
import com.vam.cco.util.StatusEnum;

@Controller
@RequestMapping("/admin/working-hours")
public class WorkingHoursController {

    private static final Logger logger = LoggerFactory.getLogger(WorkingHoursController.class);

    private final WorkingHoursService workingHoursService;

    @Autowired
    public WorkingHoursController(WorkingHoursService workingHoursService) {
        this.workingHoursService = workingHoursService;
    }

    @GetMapping("/list")
    public String list(Model model,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(required = false, name = "successMessage") String successMessage,
                        @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<WorkingHoursModel> pageData = workingHoursService.findAllWorkingHours(pageable);
        model.addAttribute("workingHoursModels", pageData.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", pageData.getTotalPages());
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "users/workingHoursList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        WorkingHoursModel modelObj = new WorkingHoursModel();
        modelObj.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        modelObj.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        modelObj.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        modelObj.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        model.addAttribute("workingHours", modelObj);
        return "users/workingHoursForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        WorkingHours workingHours = workingHoursService.findById(id).orElseThrow(() -> new RuntimeException("WorkingHours not found"));
        WorkingHoursModel modelObj = new WorkingHoursModel();
        modelObj.setWorkingHoursId(workingHours.getWorkingHoursId());
        modelObj.setLocation(workingHours.getLocation());
        modelObj.setHoursPerMonth(workingHours.getHoursPerMonth());
        modelObj.setCreatedBy(workingHours.getCreatedBy());
        modelObj.setModifiedBy(workingHours.getModifiedBy());
        modelObj.setCreatedDate(workingHours.getCreatedDate());
        modelObj.setModifiedDate(workingHours.getModifiedDate());
        model.addAttribute("workingHours", modelObj);
        return "users/workingHoursForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute WorkingHoursModel workingHoursModel, Model model) {
        try {
            WorkingHours entity = new WorkingHours();
            Long id = workingHoursModel.getWorkingHoursId();
            entity.setWorkingHoursId(workingHoursModel.getWorkingHoursId());
            entity.setLocation(workingHoursModel.getLocation());
            entity.setHoursPerMonth(workingHoursModel.getHoursPerMonth());
            entity.setCreatedBy(workingHoursModel.getCreatedBy());
            entity.setCreatedDate(workingHoursModel.getCreatedDate());
            entity.setModifiedBy(workingHoursModel.getModifiedBy());
            entity.setModifiedDate(workingHoursModel.getModifiedDate());
            workingHoursService.save(entity);
            if (id == null) {
                model.addAttribute("successMessage", "WorkingHours saved successfully");
            } else {
                model.addAttribute("successMessage", "WorkingHours updated successfully");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error saving WorkingHours: " + e.getMessage());
        }
        return "redirect:/admin/working-hours/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        workingHoursService.deleteById(id);
        return "redirect:/admin/working-hours/list";
    }
}
