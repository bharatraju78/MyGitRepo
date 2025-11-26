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

import com.vam.cco.dao.entity.ProjectRole;
import com.vam.cco.model.ProjectRoleModel;
import com.vam.cco.services.ProjectRoleService;

@Controller
@RequestMapping("/admin/project-roles")
public class ProjectRoleController {
	
    private static final Logger logger = LoggerFactory.getLogger(ProjectRoleController.class);
    
    private final ProjectRoleService projectRoleService;

    @Autowired
    public ProjectRoleController(ProjectRoleService projectRoleService) {
        this.projectRoleService = projectRoleService;
    }

    @GetMapping("/project-role-list")
    public String listProjectRoles(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(required = false, name="successMessage") String successMessage,
                                  @RequestParam(required = false, name="errorMessage") String errorMessage) {
        logger.info("Fetching project roles with pagination. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProjectRole> projectRolePage = projectRoleService.findAll(pageable);
        model.addAttribute("projectRoles", projectRolePage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", projectRolePage.getTotalPages());
        logger.info("Total project roles found: {}", projectRolePage.getTotalElements());
        if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
        if (errorMessage != null) {
        	model.addAttribute("errorMessage", errorMessage);
        }
        return "users/projectRoleList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add project role form");
        
		ProjectRoleModel proRoleModel = new ProjectRoleModel();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		proRoleModel.setCreatedBy(authentication.getName());
		proRoleModel.setModifiedBy(authentication.getName());
		proRoleModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		proRoleModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		logger.info("ProjectRoleController::showEditProjectRole::proRoleModel = {}", proRoleModel);
		logger.info("ProjectRoleController::showEditProjectRole::end");
        model.addAttribute("projectRole", proRoleModel);
        return "users/projectRoleForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for project role id: {}", id);
        ProjectRole projectRole = projectRoleService.findById(id).orElseThrow(() -> new RuntimeException("Project Role not found"));
        model.addAttribute("projectRole", projectRole);
        return "users/projectRoleForm";
    }

    @PostMapping("/project-role-save")
    public String saveProjectRole(@ModelAttribute ProjectRoleModel projectRole, Model model) {
        logger.info("Saving project role: {}", projectRole.getRoleName());
        try {
			ProjectRole projectRoleEntity = new ProjectRole();
			Long projectRoleId = projectRole.getProjectRoleId();
			projectRoleEntity.setProjectRoleId(projectRole.getProjectRoleId());
			projectRoleEntity.setRoleName(projectRole.getRoleName());
			projectRoleEntity.setCreatedBy(projectRole.getCreatedBy());
			projectRoleEntity.setModifiedBy(projectRole.getModifiedBy());
			projectRoleEntity.setCreatedDate(projectRole.getCreatedDate());
			projectRoleEntity.setModifiedDate(projectRole.getModifiedDate());	
			
			projectRoleService.save(projectRoleEntity);
			if (projectRoleId == null) {
				model.addAttribute("successMessage", "Project role created successfully");
			} else {
				model.addAttribute("successMessage", "Project role updated successfully");
			}
		} catch (Exception e) {
			logger.error("Error saving project role: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error saving project role: " + e.getMessage());
		}
        logger.info("Project role saved successfully");
        return "redirect:/admin/project-roles/project-role-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProjectRole(@PathVariable Long id) {
        logger.info("Deleting project role with id: {}", id);
        projectRoleService.deleteById(id);
        logger.info("Project role deleted successfully");
        return "redirect:/admin/project-roles/project-role-list";
    }
}