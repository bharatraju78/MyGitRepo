package com.vam.cco.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.vam.cco.dao.entity.Employee;
import com.vam.cco.dao.entity.EmployeeAllocation;
import com.vam.cco.dao.entity.Portfolio;
import com.vam.cco.dao.entity.Project;
import com.vam.cco.dao.entity.ProjectRole;
import com.vam.cco.model.AccountModel;
import com.vam.cco.model.EmployeeAllocationModel;
import com.vam.cco.model.EmployeeModel;
import com.vam.cco.services.AccountService;
import com.vam.cco.services.EmployeeAllocationService;
import com.vam.cco.services.EmployeeService;
import com.vam.cco.services.PortfolioService;
import com.vam.cco.services.ProjectRoleService;
import com.vam.cco.services.ProjectService;
import com.vam.cco.util.CCOAppliationConstants;
import com.vam.cco.util.EmployeeStatusEnum;

@Controller
@RequestMapping("/admin/employee-allocation")
public class EmployeeAllocationController {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeAllocationController.class);
   
    private final EmployeeAllocationService employeeAllocationService;
    
    private final AccountService accountService;
    
    private final ProjectService projectService;
    
    private final EmployeeService employeeService;
    
    private final ProjectRoleService projectroleService;
    
    private final PortfolioService portfolioService;

    @Autowired
    public EmployeeAllocationController(EmployeeAllocationService employeeAllocationService,
    									AccountService accountService,
    									ProjectService projectService,
    									EmployeeService employeeService,
    									ProjectRoleService projectroleService,
    									PortfolioService portfolioService) {
        this.employeeAllocationService = employeeAllocationService;
        this.accountService = accountService;
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.projectroleService = projectroleService;
        this.portfolioService = portfolioService;
    }

    @GetMapping("/employee-allocation-list")
    public String listAllocations(Model model, HttpSession session,
    		@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String successMessage,
            @RequestParam(required = false) String errorMessage) {
        logger.info("Fetching all employee allocations");
        Long employeeId = (Long) session.getAttribute("employeeId");
        String userRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Page<AccountModel> accounts = null;
        Pageable pageable = PageRequest.of(page - 1, size);
        if (userRole.contains("ROLE_ADMIN") || userRole.contains("ROLE_POWER_USER")) {
        	accounts = accountService.findAllActiveAccounts(pageable);
		} else {
			 logger.info("userRole ==> {}", userRole);
			 String userRoleStr = userRole.replace("[", "").replace("]", "").replace("ROLE_", "").replace("_", " ");
			 logger.info("userRoleStr ==> {}", userRoleStr);
			 logger.info("leaderShipList ==> {}", CCOAppliationConstants.leaderShipList);
			 Boolean isMatched = CCOAppliationConstants.leaderShipList.stream().anyMatch(s -> s.equalsIgnoreCase(userRoleStr));
			 if(isMatched) {
				Portfolio portfolio = portfolioService.getPortfolioByEmployeeId(employeeId);
				if(null != portfolio) {
					model.addAttribute("portfolioName", portfolio.getName());
					List<Long> portfolioIds = portfolioService.getPortfolioIdsByEmployeeId(employeeId);
					accounts = accountService.findAccountsByProtfolio(portfolioIds, pageable);
				}
			} else {
				logger.info("Fetching accounts for employee id: {}", employeeId);
				accounts = accountService.findAccountsByEmployeeId(employeeId, pageable);
			}
		}
        model.addAttribute("accounts", accounts.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", accounts.getTotalPages());
        if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
        if (errorMessage != null) {
        	model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("Total accounts found: {}", accounts.getTotalElements());
        return "users/employeeAllocationList";
    }
    
    @GetMapping("/projects-list/{accountId}")
    public String projectsList(@PathVariable Long accountId, Model model) {
        logger.info("Fetching all project list");
        List<Project> projects = projectService.findByAccountId(accountId);
        Account account = accountService.findById(accountId).orElse(null);
        if (account != null) {
			model.addAttribute("accountName", account.getAccName());
		}
        model.addAttribute("projects", projects);
        model.addAttribute("empAll", true);
        return "users/projectList";
    }

    @GetMapping("/employee-list/{projectId}")
    public String employeeList(@PathVariable Long projectId, Model model,
    		@RequestParam(required = false) String successMessage,
			@RequestParam(required = false) String errorMessage) {
		logger.info("Fetching all employees for project id: {}", projectId);
		List<Long> projectRoleIds = projectroleService.findLeadsAndAssociates(
				Arrays.asList(CCOAppliationConstants.EXECUTIVE_VICE_PRESIDENT, 
						CCOAppliationConstants.SENIOR_VICE_PRESIDENT, 
						CCOAppliationConstants.VICE_PRESIDENT, 
						CCOAppliationConstants.ASSOCIATE_VICE_PRESIDENT,
						CCOAppliationConstants.SENIOR_DIRECTOR, 
						CCOAppliationConstants.DIRECTOR, 
						CCOAppliationConstants.ASSOCIATE_DIRECTOR,
						CCOAppliationConstants.SENIOR_MANAGER));
		List<EmployeeModel> employees = employeeService.findEmployeesByProjectId(projectId, projectRoleIds);
		Project project = projectService.findById(projectId).orElse(null);
		
		if (project != null) {
			model.addAttribute("projectName", project.getName());
			model.addAttribute("projectId", project.getId());
			logger.info("Account id : {}", project.getAccount().getAccountId());
			model.addAttribute("accountId", project.getAccount().getAccountId());
		}
		model.addAttribute("employees", employees);
		model.addAttribute("empAll", true);
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
		
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
		}
		return "users/employeeList";
	}
    
    @GetMapping("/editEmployee/{empAllId}/{projectId}/{employeeId}")
    public String editEmployeeAllocation(@PathVariable Long empAllId,  @PathVariable Long projectId,
    		@PathVariable Long employeeId, Model model) {
		logger.info("Editing employee allocation for project id: {} and employee id: {}", projectId, employeeId);
		EmployeeAllocationModel employeeAllocationModel = new EmployeeAllocationModel();
		employeeAllocationModel.setProjectId(projectId);
		Project project = projectService.findById(projectId).orElse(null);
		if (project != null) {
			employeeAllocationModel.setProjectName(project.getName());
		}
		EmployeeModel employee = employeeService.findById(employeeId).orElse(null); 
		EmployeeAllocation allocation = employeeAllocationService.findById(empAllId).orElse(null);
		if (employee != null && allocation != null) {
			employeeAllocationModel.setEmpAllId(allocation.getEmpAllId());
			employeeAllocationModel.setEmpId(employee.getEmployeeId());
			employeeAllocationModel.setEmpName(employee.getName());
			employeeAllocationModel.setStatus(allocation.getStatus());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			employeeAllocationModel.setStartDateStr(dateFormat.format(allocation.getStartDate()));
			if(null != allocation.getEndDate()) {
				employeeAllocationModel.setEndDate(allocation.getEndDate());
				employeeAllocationModel.setEndDateStr(dateFormat.format(allocation.getEndDate()));
			}
			logger.info("End Date: {}", employeeAllocationModel.getEndDateStr());
			
			employeeAllocationModel.setRoleId(allocation.getRoleId());
			ProjectRole projectRole = projectroleService.findById(allocation.getRoleId()).orElse(null);
			if (projectRole != null) {
				employeeAllocationModel.setRoleName(projectRole.getRoleName());
			}
			
			
		}
		employeeAllocationModel.setEmpId(employeeId);
		
		model.addAttribute("employeeAllocation", employeeAllocationModel);
		model.addAttribute("projectRoles", projectroleService.findAll());
		model.addAttribute("statusList", EmployeeStatusEnum.getAllStatus());
		model.addAttribute("projectId", projectId);
		model.addAttribute("empAll", true);
		return "users/employeeForm";
	}
    @GetMapping("/add/{projectId}")
    public String showCreateForm(@PathVariable Long projectId, Model model) {
        logger.info("Showing create employee allocation form");
        EmployeeAllocationModel employeeAllocationModel = new EmployeeAllocationModel();
        Project project = projectService.findById(projectId).orElse(null);
        if (project != null) {
			employeeAllocationModel.setProjectId(project.getId());
			employeeAllocationModel.setProjectName(project.getName());
			employeeAllocationModel.setAccountId(project.getAccount().getAccountId());
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				logger.info("AccountController::generateAccountNumber::Authentication found: {}", authentication.getName());
				employeeAllocationModel.setCreatedBy(authentication.getName());
				employeeAllocationModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeAllocationModel.setModifiedBy(authentication.getName());
				employeeAllocationModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
		}
        List<String> designationNames = getLeadersAndAssociatesDesg();
        model.addAttribute("employees", employeeService.findByDesignationName(designationNames));
        model.addAttribute("employeeAllocation", employeeAllocationModel);
        model.addAttribute("roles", projectroleService.excludeLeaderShip(Arrays.asList(
				CCOAppliationConstants.EXECUTIVE_VICE_PRESIDENT, 
				CCOAppliationConstants.SENIOR_VICE_PRESIDENT, 
				CCOAppliationConstants.VICE_PRESIDENT, 
				CCOAppliationConstants.ASSOCIATE_VICE_PRESIDENT,
				CCOAppliationConstants.SENIOR_DIRECTOR, 
				CCOAppliationConstants.DIRECTOR, 
				CCOAppliationConstants.ASSOCIATE_DIRECTOR,
				CCOAppliationConstants.SENIOR_MANAGER)));
        model.addAttribute("statusList", EmployeeStatusEnum.getAllStatus());
        return "users/employeeAllocationForm";
    }

	private List<String> getLeadersAndAssociatesDesg() {
		List<String> managerAboveList = Arrays.asList(
				CCOAppliationConstants.EXECUTIVE_VICE_PRESIDENT, 
				CCOAppliationConstants.SENIOR_VICE_PRESIDENT, 
				CCOAppliationConstants.VICE_PRESIDENT, 
				CCOAppliationConstants.ASSOCIATE_VICE_PRESIDENT,
				CCOAppliationConstants.SENIOR_DIRECTOR, 
				CCOAppliationConstants.DIRECTOR, 
				CCOAppliationConstants.ASSOCIATE_DIRECTOR,
				CCOAppliationConstants.SENIOR_MANAGER);
        List<String> designationNames = employeeService.findLeadersAndAssociates(managerAboveList);
		return designationNames;
	}

    @PostMapping("/employee-allocation-save")
    public String createAllocation(@ModelAttribute("employeeAllocationModel") EmployeeAllocationModel allocation,
    		Model model) {
        logger.info("Creating new employee allocation: {}", allocation);
        try {
        	Long empAllId = allocation.getEmpAllId();
			EmployeeAllocation employeeAllocation = new EmployeeAllocation();
			
			employeeAllocation.setVerticalId(1L);
			employeeAllocation.setAllocationType("Full Allocation");
			employeeAllocation.setAccountId(allocation.getAccountId());
			employeeAllocation.setProjectId(allocation.getProjectId());
			employeeAllocation.setRoleId(allocation.getRoleId());
			employeeAllocation.setStartDate(allocation.getStartDate());
			employeeAllocation.setEndDate(allocation.getEndDate());
			employeeAllocation.setStatus(allocation.getStatus());
			EmployeeModel employeeModel = employeeService.findById(allocation.getEmpId()).orElse(null);
			if (employeeModel != null) {
				Employee employee = employeeService.convertToEntity(employeeModel);
				employeeAllocation.setEmployee(employee);
			} 
			employeeAllocationService.save(employeeAllocation);
			if(null == empAllId) {
				model.addAttribute("successMessage", "Employee allocation created successfully.");
			} else {
				model.addAttribute("successMessage", "Employee allocation updated successfully.");
			}
		} catch (Exception e) {
			logger.error("Error while creating employee allocation: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error while creating employee allocation: " + e.getMessage());
		}
        return employeeList(allocation.getProjectId(), model, model.getAttribute("successMessage") != null ? 
				model.getAttribute("successMessage").toString() : null, 
				model.getAttribute("errorMessage") != null ? model.getAttribute("errorMessage").toString() : null);
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for employee allocation id: {}", id);
        EmployeeAllocation allocation = employeeAllocationService.findById(id).orElse(null);
        if (allocation == null) {
            logger.warn("Employee allocation with id {} not found", id);
            return "redirect:/admin/employee-allocation/employee-allocation-list";
        }
        model.addAttribute("employeeAllocation", allocation);
        return "users/employeeAllocationForm";
    }

    @PostMapping("/update")
    public String updateAllocation(@ModelAttribute("employeeAllocationModel") EmployeeAllocationModel allocation,
    		Model model) {
        logger.info("Updating employee allocation with id: {}", allocation);
        EmployeeAllocation empAllocation = null;
		try {
			empAllocation = employeeAllocationService.findById(allocation.getEmpAllId()).orElse(null);
			if (empAllocation != null) {
				empAllocation.setRoleId(allocation.getRoleId());
				empAllocation.setStatus(allocation.getStatus());
				empAllocation.setEndDate(allocation.getEndDate());
				employeeAllocationService.update(empAllocation);
			}
			model.addAttribute("successMessage", "Employee allocation updated successfully.");
		} catch (Exception e) {
			logger.error("Error while updating employee allocation: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error while updating employee allocation: " + e.getMessage());
		}
        
        return employeeList(empAllocation.getProjectId(), model,model.getAttribute("successMessage") != null ? 
				model.getAttribute("successMessage").toString() : null, 
				model.getAttribute("errorMessage") != null ? model.getAttribute("errorMessage").toString() : null);
    }

    @GetMapping("/delete/{id}")
    public String deleteAllocation(@PathVariable Long id, Model model) {
        logger.info("Deleting employee allocation with id: {}", id);
        try {
			employeeAllocationService.deleteById(id);
			model.addAttribute("successMessage", "Employee allocation deleted successfully.");
		} catch (Exception e) {
			logger.error("Error while deleting employee allocation: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error while deleting employee allocation: " + e.getMessage());
		}
        return "redirect:/admin/employee-allocation/employee-allocation-list";
    }
}