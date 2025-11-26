package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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

import com.vam.cco.dao.entity.Account;
import com.vam.cco.dao.entity.Project;
import com.vam.cco.model.ProjectModel;
import com.vam.cco.services.AccountService;
import com.vam.cco.services.ProjectService;
import com.vam.cco.util.IdGenerator;
import com.vam.cco.util.StatusEnum;

@Controller
@RequestMapping("/admin/projects")
public class ProjectController {
	
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    
    private final ProjectService projectService;
    
	private AccountService accountService;
	
	private IdGenerator idGenerator;

    @Autowired
    public ProjectController(ProjectService projectService, AccountService accountService,
    		IdGenerator idGenerator) {
        this.projectService = projectService;
        this.accountService = accountService;
        this.idGenerator = idGenerator;
    }

    @GetMapping("/project-list")
    public String listProjects(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false, name="successMessage") String successMessage,
                              @RequestParam(required = false, name="errorMessage") String errorMessage) {
        logger.info("Fetching projects with pagination. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Project> projectPage = projectService.findAll(pageable);
        model.addAttribute("projects", projectPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", projectPage.getTotalPages());
        if (successMessage != null) {
        	model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
		}
        logger.info("Total projects found: {}", projectPage.getTotalElements());
        return "users/projectList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add project form");
        
        ProjectModel projectModel = new ProjectModel();
        List<Account> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		
		projectModel.setCode(idGenerator.generateAccountId());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			projectModel.setCreatedBy(authentication.getName());
			projectModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			projectModel.setModifiedBy(authentication.getName());
			projectModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		} else {
			logger.info("ProjectController::generateProjectNumber::No authentication found");
		}
		projectModel.setStatusList(StatusEnum.getAllStatus());
		
        model.addAttribute("project", projectModel);
        return "users/projectForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for project id: {}", id);
        Project project = projectService.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        
        ProjectModel projectModel = new ProjectModel();
		projectModel.setId(project.getId());
		projectModel.setName(project.getName());
		projectModel.setDescription(project.getDescription());
		projectModel.setStatus(project.getStatus());
		projectModel.setStartDate(project.getStartDate());
		projectModel.setEndDate(project.getEndDate());
		projectModel.setCode(project.getCode());
		if(null != project.getCreatedDate()) {
			projectModel.setCreatedDate(project.getCreatedDate());
		} else {
			projectModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			if(null == project.getCreatedBy()) {
				projectModel.setCreatedBy(authentication.getName());
			} else {
				projectModel.setCreatedBy(project.getCreatedBy());
			}
			projectModel.setModifiedBy(authentication.getName());
		}
		projectModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		projectModel.setAccountId(project.getAccount() != null ? project.getAccount().getAccountId() : null);
		
		
		projectModel.setStatusList(StatusEnum.getAllStatus());
		
		List<Account> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		
		 logger.info("Showing edit form for projectModel: {}", projectModel);
        model.addAttribute("project", projectModel);
        return "users/projectForm";
    }

    @PostMapping("/project-save")
    public String saveProject(@ModelAttribute ProjectModel projectModel, Model model) {
        logger.info("Saving project: {}", projectModel.getName());
        try {
			Project project = new Project();
			project.setName(projectModel.getName());
			project.setDescription(projectModel.getDescription());
			project.setStatus(projectModel.getStatus());
			project.setStartDate(projectModel.getStartDate());
			project.setEndDate(projectModel.getEndDate());
			project.setCode(projectModel.getCode());
			project.setId(projectModel.getId());
			project.setCreatedBy(projectModel.getCreatedBy());
			project.setCreatedDate(projectModel.getCreatedDate());
			project.setModifiedBy(projectModel.getModifiedBy());
			project.setModifiedDate(projectModel.getModifiedDate());
			
			Account account = accountService.findById(projectModel.getAccountId())
					.orElseThrow(() -> new RuntimeException("Account not found"));
			project.setAccount(account);
			
			projectService.save(project);
			model.addAttribute("successMessage", "" + (projectModel.getId() == null ? "Project saved successfully" : "Project updated successfully"));
		} catch (RuntimeException e) {
			logger.error("Error saving project: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error creating Project: " + e.getMessage());
		}
		
        logger.info("Project saved successfully");
        return "redirect:/admin/projects/project-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        logger.info("Deleting project with id: {}", id);
        projectService.deleteById(id);
        logger.info("Project deleted successfully");
        return "redirect:/admin/projects/project-list";
    }
}