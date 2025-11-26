package com.vam.cco.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vam.cco.dao.entity.Account;
import com.vam.cco.dao.entity.EmployeeAllocation;
import com.vam.cco.dao.entity.Project;
import com.vam.cco.dao.entity.ProjectRole;
import com.vam.cco.model.EmployeeModel;
import com.vam.cco.model.MapAssociatesModel;
import com.vam.cco.services.AccountService;
import com.vam.cco.services.EmployeeService;
import com.vam.cco.services.MapAssociatesService;
import com.vam.cco.services.ProjectRoleService;
import com.vam.cco.services.ProjectService;
import com.vam.cco.util.CCOAppliationConstants;

@Controller
@RequestMapping("/admin/map-associates")
public class MapAssociatesController {

	private static final Logger logger = LoggerFactory.getLogger(MapAssociatesController.class);

	private final AccountService accountService;

	private final ProjectRoleService projectRoleService;

	private final EmployeeService employeeService;

	private final ProjectService projectService;
	
	private final MapAssociatesService mapAssociatesService;

	@Autowired
	public MapAssociatesController(AccountService accountService, ProjectRoleService projectRoleService,
			EmployeeService employeeService, ProjectService projectService, 
			MapAssociatesService mapAssociatesService) {
		this.accountService = accountService;
		this.projectRoleService = projectRoleService;
		this.employeeService = employeeService;
		this.projectService = projectService;
		this.mapAssociatesService = mapAssociatesService;
	}

	@GetMapping("/accountList")
	public String accountList(Model model,@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
		logger.info("MapAssociatesController::accountList::start");
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Account> accounts = accountService.findAll(pageable);
		model.addAttribute("accounts", accounts.getContent());
		model.addAttribute("page", page);
	    model.addAttribute("size", size);
	    model.addAttribute("totalPages", accounts.getTotalPages());
		model.addAttribute("fromAction", "MapAssociatesController");
		logger.info("MapAssociatesController::accountList::end");
		return "users/accountList";
	}

	@GetMapping("/accountDetails/{accountId}")
	public String accountDetails(@PathVariable("accountId") Long accountId, Model model) {
		logger.info("MapAssociatesController::accountDetails::start");
		Account account = accountService.findById(accountId).get();
		if (account != null) {
			logger.info("MapAssociatesController::accountDetails::account::{}", account);
			model.addAttribute("account", account);
			model.addAttribute("accountName", account.getAccName());
			model.addAttribute("empAll", true);

			List<Project> projectList = projectService.findByAccountId(accountId);
			model.addAttribute("projects", projectList);
			model.addAttribute("fromAction", "MapAssociatesController");
			logger.info("MapAssociatesController::accountDetails::projectList::{}", projectList);

			logger.info("MapAssociatesController::accountDetails::end");
			return "users/projectList";
		} else {
			logger.warn("Account with ID {} not found", accountId);
			model.addAttribute("errorMessage", "Account not found");
			return "error";
		}
	}

	@GetMapping("/projectMap/{projectId}/{accountId}")
	public String projectMap(@PathVariable("projectId") Long projectId, @PathVariable("accountId") Long accountId,
			Model model, @RequestParam(required = false) String successMessage,
			@RequestParam(required = false) String errorMessage) {
		logger.info("MapAssociatesController::projectMap::start");

		try {
			Project project = projectService.findById(projectId).orElse(null);
			model.addAttribute("project", project);
			model.addAttribute("projectId", projectId);
			MapAssociatesModel mapAssociatesModel = mapAssociatesService.fetchMappedAssociates(accountId, projectId);
			model.addAttribute("mapAssociatesModel", mapAssociatesModel);
			
			if(null != successMessage) {
				model.addAttribute("successMessage", successMessage);
			}
			if(null != errorMessage) {
				model.addAttribute("errorMessage", errorMessage);
			}
		} catch (Exception e) {
			logger.error("Error while fetching mapped associates: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "Error while fetching mapped associates: " + e.getMessage());
		}
		logger.info("MapAssociatesController::projectMap::end");
		return "users/mapLeaderShip";
	}

	
	@GetMapping("/addNewMapping/{projectId}/{accountId}")
	public String addNewMapping(@PathVariable("projectId") Long projectId, @PathVariable("accountId") Long accountId,Model model) {
		logger.info("MapAssociatesController::addNewMapping::start");
		Project project = projectService.findById(projectId).orElse(null);
		model.addAttribute("project", project);
		model.addAttribute("projectId", projectId);
		model.addAttribute("accountId", accountId);
		logger.info("MapAssociatesController::addNewMapping::end");
		return "users/addMapLeaderShip";
	}
	
	@GetMapping("/addNewMapping-select/{projectId}/{accountId}/{type}")
	public String addNewMappingSelect(@PathVariable("projectId") Long projectId,
			@PathVariable("accountId") Long accountId,
			@PathVariable("type") String type, Model model) {
		logger.info("MapAssociatesController::addNewMappingSelect::start");
		Project project = projectService.findById(projectId).orElse(null);
		model.addAttribute("project", project);
		model.addAttribute("projectId", projectId);
		model.addAttribute("accountId", accountId);
		
		List<ProjectRole> projectRoles = projectRoleService.findAll();
		List<ProjectRole> leaderShipRoles = new ArrayList<>();
		List<ProjectRole> directorRoles = new ArrayList<>();
		List<ProjectRole> managerRoles = new ArrayList<>();
		if("Leadership".equalsIgnoreCase(type)) {
			List<String> desgNamesList = CCOAppliationConstants.leaderShipList;
			List<EmployeeModel> leadershipList = employeeService.findByDesignationName(desgNamesList);
			
			leaderShipRoles = ((Streamable<ProjectRole>) projectRoles.stream()
					.filter(role -> CCOAppliationConstants.leaderShipList.contains(role.getRoleName().trim())))
					.toList();
			model.addAttribute("empList", leadershipList);
			model.addAttribute("type", type);
			model.addAttribute("empRoles", leaderShipRoles);
		} else if("Director".equalsIgnoreCase(type)) {
			List<String> desgNamesList = CCOAppliationConstants.directorList;
			List<EmployeeModel> managerList = employeeService.findByDesignationName(desgNamesList);
			
			managerRoles = ((Streamable<ProjectRole>) projectRoles.stream()
					.filter(role -> CCOAppliationConstants.directorList.contains(role.getRoleName().trim())))
					.toList();
			model.addAttribute("empList", managerList);
			model.addAttribute("type", type);
			model.addAttribute("empRoles", managerRoles);
		} else if("Manager".equalsIgnoreCase(type)) {
			List<String> desgNamesList = CCOAppliationConstants.managerList;
			List<EmployeeModel> directorList = employeeService.findByDesignationName(desgNamesList);
			
			directorRoles = ((Streamable<ProjectRole>) projectRoles.stream()
					.filter(role -> CCOAppliationConstants.managerList.contains(role.getRoleName().trim())))
					.toList();
			model.addAttribute("empList", directorList);
			model.addAttribute("type", type);
			model.addAttribute("empRoles", directorRoles);
		} else {
			logger.warn("Invalid type provided: {}", type);
			model.addAttribute("errorMessage", "Invalid type provided: " + type);
			return "error";
		}
		logger.info("MapAssociatesController::addNewMappingSelect::end");
		return "users/addMapLeaderShip";
	}
	
	@PostMapping("/saveMapAssociates")
	public String saveMapAssociates(MapAssociatesModel mapAssociatesModel, Model model) {
		logger.info("MapAssociatesController::saveMapAssociates::start");
		logger.info("MapAssociatesController::saveMapAssociates::mapAssociatesModel::{}", mapAssociatesModel);
		try {
			Long empAllId = mapAssociatesModel.getEmpAllId();
			mapAssociatesModel = mapAssociatesService.saveMapAssociatesModel(mapAssociatesModel);
			if(null == empAllId) {
				model.addAttribute("successMessage", "Associates mapped successfully!");
			} else {
				model.addAttribute("successMessage", "Associates updated successfully!");
			}
		} catch (Exception e) {
			logger.error("Error while saving employee allocations: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "Error while saving employee allocations: " + e.getMessage());
			return "error";
		}
		model.addAttribute("successMessage", "Associates mapped successfully!");
		logger.info("MapAssociatesController::saveMapAssociates::end");
		return "redirect:/admin/map-associates/projectMap/" + mapAssociatesModel.getProjectId() + "/" + mapAssociatesModel.getAccountId();
	}
	
	@GetMapping("/delete/{projectId}/{accountId}/{empAllId}")
	public String deleteMapping(@PathVariable("projectId") Long projectId, 
			@PathVariable("accountId") Long accountId, @PathVariable("empAllId") Long empAllId, Model model) {
		logger.info("MapAssociatesController::deleteMapping::start");
		try {
			mapAssociatesService.deleteMappedAssociates(empAllId);
			model.addAttribute("successMessage", "Mapping deleted successfully!");
		} catch (Exception e) {
			logger.error("Error while deleting mapping: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "Error while deleting mapping: " + e.getMessage());
		}
		logger.info("MapAssociatesController::deleteMapping::end");
		return "redirect:/admin/map-associates/projectMap/" + projectId + "/" + accountId;
	}
	
	@GetMapping("/showEdit/{projectId}/{accountId}/{empAllId}/{type}")
	public String editMapping(@PathVariable("projectId") Long projectId, 
			@PathVariable("accountId") Long accountId, @PathVariable("empAllId") Long empAllId,
			@PathVariable("type") String type, Model model) {
		logger.info("MapAssociatesController::showEdit::start");
		try {
			MapAssociatesModel mapAssociatesModel = new MapAssociatesModel();
			mapAssociatesModel.setAccountId(accountId);
			mapAssociatesModel.setProjectId(projectId);
			mapAssociatesModel.setEmpAllId(empAllId);
			mapAssociatesModel = mapAssociatesService.findEmpAllToEdit(mapAssociatesModel);
			logger.info("MapAssociatesController::editMapping::mapAssociatesModel::{}", mapAssociatesModel);
			if (mapAssociatesModel != null) {
				EmployeeAllocation empAllocation = mapAssociatesModel.getEmployeeAllocationList().get(0);
				mapAssociatesModel.setEmpId(empAllocation.getEmpId());
				mapAssociatesModel.setRoleId(empAllocation.getRoleId());
				mapAssociatesModel.setEmpAllId(empAllId);
				model.addAttribute("mapAssociatesModel", mapAssociatesModel);
				model.addAttribute("projectId", projectId);
				model.addAttribute("accountId", accountId);
				model.addAttribute("type", type);
				
				Project project = projectService.findById(projectId).orElse(null);
				model.addAttribute("project", project);
				
				List<ProjectRole> projectRoles = projectRoleService.findAll();
				List<ProjectRole> leaderShipRoles = new ArrayList<>();
				List<ProjectRole> directorRoles = new ArrayList<>();
				List<ProjectRole> managerRoles = new ArrayList<>();
				if("Leadership".equalsIgnoreCase(type)) {
					List<String> desgNamesList = CCOAppliationConstants.leaderShipList;
					List<EmployeeModel> leadershipList = employeeService.findByDesignationName(desgNamesList);
					
					leaderShipRoles = ((Streamable<ProjectRole>) projectRoles.stream()
							.filter(role -> CCOAppliationConstants.leaderShipList.contains(role.getRoleName().trim())))
							.toList();
					model.addAttribute("empList", leadershipList);
					model.addAttribute("type", type);
					model.addAttribute("empRoles", leaderShipRoles);
				} else if("Director".equalsIgnoreCase(type)) {
					List<String> desgNamesList = CCOAppliationConstants.directorList;
					List<EmployeeModel> managerList = employeeService.findByDesignationName(desgNamesList);
					
					managerRoles = ((Streamable<ProjectRole>) projectRoles.stream()
							.filter(role -> CCOAppliationConstants.directorList.contains(role.getRoleName().trim())))
							.toList();
					model.addAttribute("empList", managerList);
					model.addAttribute("type", type);
					model.addAttribute("empRoles", managerRoles);
				} else if("Manager".equalsIgnoreCase(type)) {
					List<String> desgNamesList = CCOAppliationConstants.managerList;
					List<EmployeeModel> directorList = employeeService.findByDesignationName(desgNamesList);
					
					directorRoles = ((Streamable<ProjectRole>) projectRoles.stream()
							.filter(role -> CCOAppliationConstants.managerList.contains(role.getRoleName().trim())))
							.toList();
					model.addAttribute("empList", directorList);
					model.addAttribute("type", type);
					model.addAttribute("empRoles", directorRoles);
				} else {
					logger.warn("Invalid type provided: {}", type);
					model.addAttribute("errorMessage", "Invalid type provided: " + type);
					return "error";
				}
			} else {
				logger.warn("No mapping found for empAllId: {}", empAllId);
				model.addAttribute("errorMessage", "No mapping found for the selected employee.");
			}
		} catch (Exception e) {
			logger.error("Error while deleting mapping: {}", e.getMessage(), e);
			model.addAttribute("errorMessage", "Error while deleting mapping: " + e.getMessage());
		}
		logger.info("MapAssociatesController::showEdit::end");
		return "users/addMapLeaderShip";
	}
			
}
