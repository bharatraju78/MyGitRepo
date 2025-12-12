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

import com.vam.cco.dao.entity.Department;
import com.vam.cco.model.DepartmentModel;
import com.vam.cco.services.DepartmentService;
import com.vam.cco.util.StatusEnum;

@Controller
@RequestMapping("/admin/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department-list")
    public String listDepartments(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(required = false, name = "successMessage") String successMessage,
                                  @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        logger.info("Fetching departments with pagination. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<DepartmentModel> deptPage = departmentService.findAllDepartments(pageable);
        model.addAttribute("departmentModels", deptPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", deptPage.getTotalPages());
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("Total departments found: {}", deptPage.getTotalElements());
        return "users/departmentList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add department form");
        DepartmentModel deptModel = new DepartmentModel();
        deptModel.setDepartmentStatus(StatusEnum.ACTIVE.getStatus());
        deptModel.setDepartmentStatusList(StatusEnum.getAllStatus());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            deptModel.setCreatedBy(authentication.getName());
            deptModel.setModifiedBy(authentication.getName());
            deptModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            deptModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }
        model.addAttribute("department", deptModel);
        return "users/departmentForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for department id: {}", id);
        Department department = departmentService.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        DepartmentModel deptModel = new DepartmentModel();
        deptModel.setDepartmentId(department.getDepartmentId());
        deptModel.setDepartmentCode(department.getDepartmentCode());
        deptModel.setDepartmentName(department.getDepartmentName());
        deptModel.setDepartmentStatus(department.getDepartmentStatus());
        deptModel.setCreatedBy(department.getCreatedBy());
        if (department.getCreatedDate() != null) {
            deptModel.setCreatedDate(department.getCreatedDate());
        } else {
            deptModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (deptModel.getCreatedBy() == null) {
                deptModel.setCreatedBy(authentication.getName());
            }
            if (deptModel.getModifiedBy() == null) {
                deptModel.setModifiedBy(authentication.getName());
            }
        }
        if (department.getModifiedDate() != null) {
            deptModel.setModifiedDate(department.getModifiedDate());
        } else {
            deptModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }
        deptModel.setDepartmentStatusList(StatusEnum.getAllStatus());
        model.addAttribute("department", deptModel);
        return "users/departmentForm";
    }

    @PostMapping("/department-save")
    public String saveDepartment(@ModelAttribute DepartmentModel departmentModel, Model model) {
        logger.info("Saving department: {}", departmentModel.getDepartmentName());
        try {
            Department department = new Department();
            Long departmentId = departmentModel.getDepartmentId();
            department.setDepartmentId(departmentModel.getDepartmentId());
            department.setDepartmentCode(departmentModel.getDepartmentCode());
            department.setDepartmentName(departmentModel.getDepartmentName());
            department.setDepartmentStatus(departmentModel.getDepartmentStatus());
            department.setCreatedBy(departmentModel.getCreatedBy());
            department.setCreatedDate(departmentModel.getCreatedDate());
            department.setModifiedBy(departmentModel.getModifiedBy());
            department.setModifiedDate(departmentModel.getModifiedDate());
            departmentService.save(department);
            if (departmentId == null) {
                model.addAttribute("successMessage", "Department saved successfully");
            } else {
                model.addAttribute("successMessage", "Department updated successfully");
            }
        } catch (Exception e) {
            logger.error("Error saving department: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error creating Department:" + e.getMessage());
        }
        return "redirect:/admin/department/department-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        logger.info("Deleting department with id: {}", id);
        departmentService.deleteById(id);
        logger.info("Department deleted successfully");
        return "redirect:/admin/department/department-list";
    }
}