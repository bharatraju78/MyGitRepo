package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.vam.cco.dao.entity.EmployeeCompensation;
import com.vam.cco.dao.entity.EmployeeDesignation;
import com.vam.cco.dao.entity.EmployeeGrade;
import com.vam.cco.dao.entity.EmployeeOffBoading;
import com.vam.cco.model.EmployeeModel;
import com.vam.cco.services.DesignationService;
import com.vam.cco.services.EmployeeService;
import com.vam.cco.services.GradeService;
import com.vam.cco.util.CCOAppliationConstants;
import com.vam.cco.util.EmployeeStatusEnum;
import com.vam.cco.util.StatusEnum;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	private final EmployeeService employeeService;

	private final GradeService gradeService;

	private final DesignationService designationService;

	@Autowired
	public EmployeeController(EmployeeService employeeService, GradeService gradeService,
			DesignationService designationService) {
		this.employeeService = employeeService;
		this.gradeService = gradeService;
		this.designationService = designationService;
	}

	@GetMapping("/employee-list")
	public String listEmployees(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all employees (paginated)");
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<EmployeeModel> employeePage = employeeService.findAll(pageable);
		model.addAttribute("employeePage", employeePage);
		model.addAttribute("employees", employeePage.getContent());
		
		model.addAttribute("page", page);
		model.addAttribute("size", 10);
		model.addAttribute("totalPages", employeePage.getTotalPages());
		model.addAttribute("empAll", null);
		return "users/employeeList";
	}

	@GetMapping("/add")
	public String showCreateForm(Model model) {
		logger.info("Showing create employee form start");

		Authentication authentication = setEmployeeModel(model);

		setEmployeeDesignation(model, authentication);

		setEmployeeGrade(model, authentication);

		setEmployeeCompensation(model, authentication);

		setEmployeeOffBoading(model, authentication);
		logger.info("Showing create employee form end");
		return "users/employeeForm";
	}

	private void setEmployeeOffBoading(Model model, Authentication authentication) {
		EmployeeOffBoading employeeOffBoarding = new EmployeeOffBoading();
		employeeOffBoarding.setCreatedBy(authentication.getName());
		employeeOffBoarding.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		employeeOffBoarding.setModifiedBy(authentication.getName());
		employeeOffBoarding.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		model.addAttribute("empOffBoarding", employeeOffBoarding);
	}

	private void setEmployeeCompensation(Model model, Authentication authentication) {
		EmployeeCompensation employeeCompensation = new EmployeeCompensation();
		employeeCompensation.setCreatedBy(authentication.getName());
		employeeCompensation.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		employeeCompensation.setModifiedBy(authentication.getName());
		employeeCompensation.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		model.addAttribute("empComp", employeeCompensation);
	}

	private void setEmployeeGrade(Model model, Authentication authentication) {
		EmployeeGrade employeeGrade = new EmployeeGrade();
		employeeGrade.setCreatedBy(authentication.getName());
		employeeGrade.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		employeeGrade.setModifiedBy(authentication.getName());
		employeeGrade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		model.addAttribute("empGrade", employeeGrade);
		model.addAttribute("grades", gradeService.findAll());
	}

	private void setEmployeeDesignation(Model model, Authentication authentication) {
		EmployeeDesignation employeeDesg = new EmployeeDesignation();
		employeeDesg.setCreatedBy(authentication.getName());
		employeeDesg.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		employeeDesg.setModifiedBy(authentication.getName());
		employeeDesg.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		model.addAttribute("empDesg", employeeDesg);
		model.addAttribute("designations", designationService.findAll());
	}

	private Authentication setEmployeeModel(Model model) {
		EmployeeModel employeeModel = new EmployeeModel();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		employeeModel.setCreatedBy(authentication.getName());
		employeeModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		employeeModel.setModifiedBy(authentication.getName());
		employeeModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		employeeModel.setGrades(gradeService.findAll());
		employeeModel.setDesignations(designationService.findAll());
		employeeModel.setStatusList(StatusEnum.getAllStatus());
		model.addAttribute("employee", employeeModel);
		model.addAttribute("status", EmployeeStatusEnum.getAllStatus());
		return authentication;
	}

	@PostMapping("/employeeDetails-save")
	public String createEmployee(@ModelAttribute("employee") EmployeeModel employeeModel, Model model) {
		logger.info("Creating new employee: {}", employeeModel);
		String jspPath = null;
		String requestType = employeeModel.getRequestType();
		try {
			jspPath = "users/employeeForm";
			Long employeeId = employeeModel.getEmployeeId();
			employeeModel = employeeService.save(employeeModel);
			if(null == employeeId) {
				model.addAttribute("successMessage", "Employee created successfully!");
			} else {
				model.addAttribute("successMessage", "Employee updated successfully!");
			}
			if (null == requestType || "".equals(requestType)) {
				model.addAttribute("activeTab", "Employee");
				model.addAttribute("actionFrom", employeeModel.getActionFrom());
				model.addAttribute("status", EmployeeStatusEnum.getAllStatus());
				model.addAttribute("grades", gradeService.findAll());
				model.addAttribute("designations", designationService.findAll());
				model.addAttribute("employee", employeeModel);
			} else {
				employeeModel.setEmployeeId(null);
				model.addAttribute("requestType", requestType);
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error creating employee: " + e.getMessage());
			logger.error("Error creating employee: {}", e.getMessage());
			if (null == requestType || "".equals(requestType)) {
				jspPath = "users/employeeForm";
			} else {
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		}
		if (null != employeeModel.getEmployeeId()) {
			setEmployeeInfo(employeeModel.getEmployeeId(), model);
		}
		return jspPath;
	}

	@PostMapping("/employeeDesignation-save")
	public String createEmployeeDesignation(@ModelAttribute("empDesg") EmployeeDesignation employeeDesg, Model model) {
		logger.info("Creating new employee: {}", employeeDesg);
		String jspPath = null;
		String requestType = employeeDesg.getRequestType();
		try {
			jspPath = "users/employeeForm";
			Long id = employeeDesg.getId();
			employeeService.saveEmployeeDesignation(employeeDesg);
			if (null == id) {
				model.addAttribute("successMessage", "Employee Designation created successfully!");
			} else {
				model.addAttribute("successMessage", "Employee Designation updated successfully!");
			}
			if (null == requestType || "".equals(requestType)) {
				model.addAttribute("actionFrom", employeeDesg.getActionFrom());
				jspPath = "users/employeeForm";
				setEmployeeInfo(employeeDesg.getEmployeeId(), model);
				model.addAttribute("actionFrom", employeeDesg.getActionFrom());
				model.addAttribute("hideAddBtn", employeeDesg.getHideAddBtn());
			} else {
				model.addAttribute("requestType", requestType);
				model.addAttribute("actionFrom", "HR");
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error creating Employee Designation: " + e.getMessage());
			logger.error("Error creating employee: {}", e.getMessage());
			if (null == requestType || "".equals(requestType)) {
				jspPath = "users/employeeForm";
			} else {
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		}
		model.addAttribute("activeTab", "Designation");
		if (null != employeeDesg.getEmployeeId()) {
			setEmployeeInfo(employeeDesg.getEmployeeId(), model);
		}
		return jspPath;
	}

	@PostMapping("/employeeGrade-save")
	public String createEmployeeGrade(@ModelAttribute("empGrad") EmployeeGrade employeeGrade, Model model) {
		logger.info("Creating new employee: {}", employeeGrade);
		String jspPath = null;
		String requestType = employeeGrade.getRequestType();
		try {
			
			jspPath = "users/employeeForm";
			Long id = employeeGrade.getId();
			employeeService.saveEmployeeGrade(employeeGrade);
			if (null == id) {
				model.addAttribute("successMessage", "Employee Grade created successfully!");
			} else {
				model.addAttribute("successMessage", "Employee Grade updated successfully!");
			}
			if (null == requestType || "".equals(requestType)) {
				setEmployeeInfo(employeeGrade.getEmployeeId(), model);
				model.addAttribute("actionFrom", employeeGrade.getActionFrom());
			} else {
				model.addAttribute("requestType", requestType);
				model.addAttribute("actionFrom", "HR");
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error creating Employee Grade: " + e.getMessage());
			logger.error("Error creating employee: {}", e.getMessage());
			if (null == requestType || "".equals(requestType)) {
				jspPath = "users/employeeForm";
			} else {
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		}
		model.addAttribute("activeTab", "Grade");
		if (null != employeeGrade.getEmployeeId()) {
			setEmployeeInfo(employeeGrade.getEmployeeId(), model);
		}
		return jspPath;
	}

	@PostMapping("/employeeCompensation-save")
	public String createEmployeeCompensation(@ModelAttribute("empComp") EmployeeCompensation employeeComp,
			Model model) {
		logger.info("Creating new employee: {}", employeeComp);
		String jspPath = null;
		String requestType = employeeComp.getRequestType();
		try {
			
			logger.info("employeeCompensation employeeComp: {}", employeeComp);
			Long id = employeeComp.getId();
			employeeService.saveEmployeeCompensation(employeeComp);
			if (null == id) {
				model.addAttribute("successMessage", "Employee Compensation created successfully!");
			} else {
				model.addAttribute("successMessage", "Employee Compensation updated successfully!");
			}
			if (null == requestType || "".equals(requestType)) {
				jspPath = "users/employeeForm";
				setEmployeeInfo(employeeComp.getEmployeeId(), model);
				model.addAttribute("actionFrom", employeeComp.getActionFrom());
			} else {
				model.addAttribute("requestType", requestType);
				model.addAttribute("actionFrom", "HR");
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}

		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error creating Employee Compensation: " + e.getMessage());
			logger.error("Error creating employee: {}", e.getMessage());
			if (null == requestType || "".equals(requestType)) {
				jspPath = "users/employeeForm";
			} else {
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		}
		model.addAttribute("activeTab", "Compensation");
		if (null != employeeComp.getEmployeeId()) {
			setEmployeeInfo(employeeComp.getEmployeeId(), model);
		}
		return jspPath;
	}

	@PostMapping("/employeeOffBoard-save")
	public String createEmployeeOffBoard(@ModelAttribute("empOffBoarding") EmployeeOffBoading employeeOffBoarding,
			Model model) {
		logger.info("Creating new employee: {}", employeeOffBoarding);
		String jspPath = null;
		String requestType = employeeOffBoarding.getRequestType();
		try {
			jspPath = "users/employeeForm";
			Long id = employeeOffBoarding.getId();
			employeeService.saveEmployeeOffBoading(employeeOffBoarding);
			if (null == id) {
				model.addAttribute("successMessage", "Employee Off Boarding created successfully!");
			} else {
				model.addAttribute("successMessage", "Employee Off Boarding updated successfully!");
			}
			if (null == requestType || "".equals(requestType)) {
				setEmployeeInfo(employeeOffBoarding.getEmployeeId(), model);
				model.addAttribute("actionFrom", employeeOffBoarding.getActionFrom());
			} else {
				model.addAttribute("requestType", requestType);
				model.addAttribute("actionFrom", "HR");
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error creating Employee Off Boarding: " + e.getMessage());
			logger.error("Error creating employee: {}", e.getMessage());
			if (null == requestType || "".equals(requestType)) {
				jspPath = "users/employeeForm";
			} else {
				jspPath = "redirect:/admin/empUpdateRequest/list";
			}
		}
		model.addAttribute("activeTab", "OffBoarding");
		if (null != employeeOffBoarding.getEmployeeId()) {
			setEmployeeInfo(employeeOffBoarding.getEmployeeId(), model);
		}
		return jspPath;
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		logger.info("Showing edit form for employee id: {}", id);
		setEmployeeInfo(id, model);
		return "users/employeeForm";
	}

	@GetMapping("/editHR/{employeeId}/{requestId}/{requestType}")
	public String showEditFormHR(@PathVariable Long employeeId, @PathVariable Long requestId,
			@PathVariable String requestType, Model model) {
		logger.info("Showing edit form HR for employee id: {}", employeeId);
		EmployeeModel employee = employeeService.findById(employeeId).orElse(null);
		setEmployeeInformation(employeeId, model, requestType);
		model.addAttribute("actionFrom", "HR");
		model.addAttribute("requestId", requestId);
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("employee", employee);
		return "users/employeeForm";
	}

	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") EmployeeModel employeeModel) {
		logger.info("Updating employee with id: {}", id);
		employeeService.update(id, employeeModel);
		return "redirect:/admin/employees/employee-list";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id, Model model) {
		logger.info("Deleting employee start with id: {}", id);
		try {
			employeeService.deleteById(id);
			model.addAttribute("successMessage", "Employee deleted successfully!");
		} catch (Exception e) {
			logger.error("Error deleting employee with id {}: {}", id, e.getMessage());
			model.addAttribute("errorMessage", "Error deleting employee: " + e.getMessage());
		}
		logger.info("Deleting employee end with id: {}", id);
		return "redirect:/admin/employees/employee-list";
	}

	@GetMapping("/addNewEmpRecord/{id}/{activeTab}")
	public String addNewEmpRecord(@PathVariable Long id, @PathVariable String activeTab, Model model) {
		logger.info("Adding new employee record for id: {} and actionFrom: {}", id, activeTab);
		setEmployeeInfo(id, model, activeTab);
		model.addAttribute("actionFrom", "HR");
		model.addAttribute("activeTab", activeTab);
		return "users/employeeForm";
	}

	@GetMapping("/employeeDesignation-list/{id}")
	public String listEmployeeDesignation(@PathVariable Long id, Model model) {
		logger.info("Fetching employee designations for employee id: {}", id);
		setEmployeeInfo(id, model, "designationHistory");
		model.addAttribute("employeeId", id);
		model.addAttribute("activeTab", "Designation");
		return "users/employeeForm";
	}

	@GetMapping("/employeeGrade-list/{id}")
	public String listEmployeeGrade(@PathVariable Long id, Model model) {
		logger.info("Fetching employee grade for employee id: {}", id);
		setEmployeeInfo(id, model, "gradeHistory");
		model.addAttribute("employeeId", id);
		model.addAttribute("activeTab", "Grade");
		return "users/employeeForm";
	}

	@GetMapping("/employeeComp-list/{id}")
	public String listEmployeeComp(@PathVariable Long id, Model model) {
		logger.info("Fetching employee grade for employee id: {}", id);
		setEmployeeInfo(id, model, "compHistory");
		model.addAttribute("employeeId", id);
		model.addAttribute("activeTab", "Compensation");
		return "users/employeeForm";
	}

	@GetMapping("/employeeOffBoarding-list/{id}")
	public String listEmployeeOffBoarding(@PathVariable Long id, Model model) {
		logger.info("Fetching employee OffBoarding for employee id: {}", id);
		setEmployeeInfo(id, model, "offBoardingHistory");
		model.addAttribute("employeeId", id);
		model.addAttribute("activeTab", "OffBoarding");
		return "users/employeeForm";
	}

	private void setEmployeeInfo(Long id, Model model) {
		logger.info("EmployeeController::setEmployeeInfo::start id: {}", id);
		try {
			EmployeeModel employee = employeeService.findById(id).orElse(null);
			logger.info("EmployeeController::setEmployeeInfo::employee: {}", employee);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (null == employee.getCreatedBy()) {
				employee.setCreatedBy(authentication.getName());
			} else {
				employee.setCreatedBy(employee.getCreatedBy());
			}
			if (null == employee.getCreatedDate()) {
				employee.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			} else {
				employee.setCreatedDate(employee.getCreatedDate());
			}

			List<EmployeeDesignation> employeeDesgs = employeeService.findEmployeeDesignationById(id);
			EmployeeDesignation employeeDesg = null;
			if (employeeDesgs == null || employeeDesgs.isEmpty()) {
				employeeDesg = new EmployeeDesignation();
				employeeDesg.setCreatedBy(authentication.getName());
				employeeDesg.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeDesg.setModifiedBy(authentication.getName());
				employeeDesg.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			} else {
				employeeDesg = employeeDesgs.get(0);
				employeeDesg.setModifiedBy(authentication.getName());
				employeeDesg.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
			logger.info("EmployeeController::setEmployeeInfo::employeeDesg: {}", employeeDesg);

			List<EmployeeGrade> employeeGrades = employeeService.findEmployeeGradeById(id);
			EmployeeGrade employeeGrade = null;
			if (employeeGrades == null || employeeGrades.isEmpty()) {
				employeeGrade = new EmployeeGrade();
				employeeGrade.setCreatedBy(authentication.getName());
				employeeGrade.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeGrade.setModifiedBy(authentication.getName());
				employeeGrade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			} else {
				employeeGrade = employeeGrades.get(0);
				employeeGrade.setModifiedBy(authentication.getName());
				employeeGrade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
			logger.info("EmployeeController::setEmployeeInfo::employeeGrade: {}", employeeGrade);

			List<EmployeeCompensation> employeeCompensations = employeeService.findEmployeeCompensationById(id);
			EmployeeCompensation employeeCompensation = null;
			if (employeeCompensations == null || employeeCompensations.isEmpty()) {
				employeeCompensation = new EmployeeCompensation();
				employeeCompensation.setCreatedBy(authentication.getName());
				employeeCompensation.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeCompensation.setModifiedBy(authentication.getName());
				employeeCompensation.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				List<EmployeeCompensation> employeeCompensationList = new ArrayList<>();
				employeeCompensationList.add(new EmployeeCompensation());
				employeeCompensation.setEmployeeCompensations(employeeCompensationList);
				
			} else {
				employeeCompensation = new EmployeeCompensation();
				employeeCompensation.setEmployeeCompensations(employeeCompensations);
			}
			logger.info("EmployeeController::setEmployeeInfo::employeeCompensation: {}", employeeCompensation);

			List<EmployeeOffBoading> employeeOffBoardings = employeeService.findEmployeeOffBoadingById(id);
			EmployeeOffBoading employeeOffBoarding = null;
			if (employeeOffBoardings == null || employeeOffBoardings.isEmpty()) {
				employeeOffBoarding = new EmployeeOffBoading();
				employeeOffBoarding.setCreatedBy(authentication.getName());
				employeeOffBoarding.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeOffBoarding.setModifiedBy(authentication.getName());
				employeeOffBoarding.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			} else {
				employeeOffBoarding = employeeOffBoardings.get(0);
				employeeOffBoarding.setModifiedBy(authentication.getName());
				employeeOffBoarding.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
			logger.info("EmployeeController::setEmployeeInfo::employeeOffBoarding: {}", employeeOffBoarding);

			model.addAttribute("employee", employee);
			model.addAttribute("empDesg", employeeDesg);
			model.addAttribute("empGrade", employeeGrade);
			model.addAttribute("empComp", employeeCompensation);
			model.addAttribute("empOffBoarding", employeeOffBoarding);
			model.addAttribute("status", EmployeeStatusEnum.getAllStatus());
			model.addAttribute("grades", gradeService.findAll());
			model.addAttribute("designations", designationService.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("EmployeeController::setEmployeeInfo::end ");
	}

	private void setEmployeeInfo(Long id, Model model, String activeTab) {
		logger.info("EmployeeController::setEmployeeInfo::start id: {}", id);
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			EmployeeModel employee = setEmployee(id, authentication);
			EmployeeDesignation employeeDesg = null;
			if ("designationHistory".equals(activeTab)) {
				List<EmployeeDesignation> employeeDesgs = employeeService.findEmpDesgHis(id);
				model.addAttribute("empDesgs", employeeDesgs);
				model.addAttribute("designationHistory", true);
			} else {
				if ("Designation".equals(activeTab)) {
					employeeDesg = setEmployeeDesignation(id, authentication, true);
					model.addAttribute("hideAddBtn", true);
				} else {
					employeeDesg = setEmployeeDesignation(id, authentication, false);
				}
			}
			EmployeeGrade employeeGrade = null;
			if ("gradeHistory".equals(activeTab)) {
				List<EmployeeGrade> employeeGrades = employeeService.findEmpGradeHis(id);
				model.addAttribute("empGrades", employeeGrades);
				model.addAttribute("gradeHistory", true);
			} else {
				if ("Grade".equals(activeTab)) {
					employeeGrade = setEmployeeGrade(id, authentication, true);
					model.addAttribute("hideAddBtn", true);
				} else {
					employeeGrade = setEmployeeGrade(id, authentication, false);
				}
			}

			EmployeeCompensation employeeCompensation = null;
			if ("compHistory".equals(activeTab)) {
				List<EmployeeCompensation> employeeComps = employeeService.findEmpCompHis(id);
				logger.info("EmployeeController::setEmployeeInfo::employeeComps: {}", employeeComps);
				model.addAttribute("empComps", employeeComps);
				model.addAttribute("compHistory", true);
			} else {
				if ("Compensation".equals(activeTab)) {
					employeeCompensation = setEmployeeCompensation(id, authentication, true);
					model.addAttribute("hideAddBtn", true);
				} else {
					employeeCompensation = setEmployeeCompensation(id, authentication, false);
				}
			}

			EmployeeOffBoading employeeOffBoarding = null;
			if ("offBoardingHistory".equals(activeTab)) {
				List<EmployeeOffBoading> employeeOffBoardings = employeeService.findEmpOffBoardingHis(id);
				model.addAttribute("empOffBoardings", employeeOffBoardings);
				model.addAttribute("offBoardingHistory", true);
			} else {
				if ("OffBoarding".equals(activeTab)) {
					employeeOffBoarding = setEmployeeOffBoading(id, authentication, true);
					model.addAttribute("hideAddBtn", true);
				} else {
					employeeOffBoarding = setEmployeeOffBoading(id, authentication, false);
				}
			}
			logger.info("EmployeeController::setEmployeeInfo::employeeOffBoarding: {}", employeeOffBoarding);

			model.addAttribute("employee", employee);
			model.addAttribute("empDesg", employeeDesg);
			model.addAttribute("empGrade", employeeGrade);
			model.addAttribute("empComp", employeeCompensation);
			model.addAttribute("empOffBoarding", employeeOffBoarding);
			model.addAttribute("status", EmployeeStatusEnum.getAllStatus());
			model.addAttribute("grades", gradeService.findAll());
			model.addAttribute("designations", designationService.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("EmployeeController::setEmployeeInfo::end ");
	}

	private EmployeeOffBoading setEmployeeOffBoading(Long id, Authentication authentication, Boolean createNew) {
		logger.info("EmployeeController::setEmployeeOffBoading::start");
		EmployeeOffBoading employeeOffBoarding = null;
		if (!createNew) {
			List<EmployeeOffBoading> employeeOffBoardings = employeeService.findEmployeeOffBoadingById(id);
			if (employeeOffBoardings == null || employeeOffBoardings.isEmpty()) {
				employeeOffBoarding = new EmployeeOffBoading();
				employeeOffBoarding.setCreatedBy(authentication.getName());
				employeeOffBoarding.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeOffBoarding.setModifiedBy(authentication.getName());
				employeeOffBoarding.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			} else {
				employeeOffBoarding = employeeOffBoardings.get(0);
				employeeOffBoarding.setModifiedBy(authentication.getName());
				employeeOffBoarding.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
		} else {
			employeeOffBoarding = new EmployeeOffBoading();
			employeeOffBoarding.setCreatedBy(authentication.getName());
			employeeOffBoarding.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			employeeOffBoarding.setModifiedBy(authentication.getName());
			employeeOffBoarding.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}

		return employeeOffBoarding;
	}

	private EmployeeCompensation setEmployeeCompensation(Long id, Authentication authentication, Boolean createNew) {
		logger.info("EmployeeController::setEmployeeCompensation::start");
		EmployeeCompensation employeeCompensation = null;
		if (!createNew) {
			List<EmployeeCompensation> employeeCompensations = employeeService.findEmployeeCompensationById(id);
			if (employeeCompensations == null || employeeCompensations.isEmpty()) {
				employeeCompensation = new EmployeeCompensation();
				employeeCompensation.setCreatedBy(authentication.getName());
				employeeCompensation.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeCompensation.setModifiedBy(authentication.getName());
				employeeCompensation.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			} else {
				employeeCompensation = employeeCompensations.get(0);
				employeeCompensation.setModifiedBy(authentication.getName());
				employeeCompensation.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
		} else {
			employeeCompensation = new EmployeeCompensation();
			employeeCompensation.setCreatedBy(authentication.getName());
			employeeCompensation.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			employeeCompensation.setModifiedBy(authentication.getName());
			employeeCompensation.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		logger.info("EmployeeController::setEmployeeInfo::employeeCompensation: {}", employeeCompensation);
		return employeeCompensation;
	}

	private EmployeeGrade setEmployeeGrade(Long id, Authentication authentication, Boolean createNew) {
		logger.info("EmployeeController::setEmployeeGrade::start");
		EmployeeGrade employeeGrade = null;
		if (!createNew) {
			List<EmployeeGrade> employeeGrades = employeeService.findEmployeeGradeById(id);
			if (employeeGrades == null || employeeGrades.isEmpty()) {
				employeeGrade = new EmployeeGrade();
				employeeGrade.setCreatedBy(authentication.getName());
				employeeGrade.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeGrade.setModifiedBy(authentication.getName());
				employeeGrade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			} else {
				employeeGrade = employeeGrades.get(0);
				employeeGrade.setModifiedBy(authentication.getName());
				employeeGrade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
		} else {
			employeeGrade = new EmployeeGrade();
			employeeGrade.setCreatedBy(authentication.getName());
			employeeGrade.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			employeeGrade.setModifiedBy(authentication.getName());
			employeeGrade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		logger.info("EmployeeController::setEmployeeInfo::employeeGrade: {}", employeeGrade);
		return employeeGrade;
	}

	private EmployeeDesignation setEmployeeDesignation(Long id, Authentication authentication, Boolean createNew) {
		logger.info("EmployeeController::setEmployeeDesignation::start");
		EmployeeDesignation employeeDesg = null;
		if (!createNew) {
			List<EmployeeDesignation> employeeDesgs = employeeService.findEmployeeDesignationById(id);
			if (employeeDesgs == null || employeeDesgs.isEmpty()) {
				employeeDesg = new EmployeeDesignation();
				employeeDesg.setCreatedBy(authentication.getName());
				employeeDesg.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeDesg.setModifiedBy(authentication.getName());
				employeeDesg.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			} else {
				employeeDesg = employeeDesgs.get(0);
				employeeDesg.setModifiedBy(authentication.getName());
				employeeDesg.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
		} else {
			employeeDesg = new EmployeeDesignation();
			employeeDesg.setCreatedBy(authentication.getName());
			employeeDesg.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			employeeDesg.setModifiedBy(authentication.getName());
			employeeDesg.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		logger.info("EmployeeController::setEmployeeInfo::employeeDesg: {}", employeeDesg);
		return employeeDesg;
	}

	private EmployeeModel setEmployee(Long id, Authentication authentication) {
		EmployeeModel employee = employeeService.findById(id).orElse(null);
		logger.info("EmployeeController::setEmployeeInfo::employee: {}", employee);
		if (null == employee.getCreatedBy()) {
			employee.setCreatedBy(authentication.getName());
		} else {
			employee.setCreatedBy(employee.getCreatedBy());
		}
		if (null == employee.getCreatedDate()) {
			employee.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		} else {
			employee.setCreatedDate(employee.getCreatedDate());
		}
		return employee;
	}

	private void setEmployeeInformation(Long id, Model model, String requestType) {
		logger.info("EmployeeController::setEmployeeInfo::start id: {}", id);
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (CCOAppliationConstants.EMPLOYEE_DETAILS.equals(requestType)) {
				EmployeeModel employee = new EmployeeModel();
				logger.info("EmployeeController::setEmployeeInfo::employee: {}", employee);
				employee.setCreatedBy(authentication.getName());
				employee.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employee.setModifiedBy(authentication.getName());
				employee.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				model.addAttribute("employee", employee);
			} else if (CCOAppliationConstants.DESIGNATION.equals(requestType)) {
				EmployeeDesignation employeeDesg = new EmployeeDesignation();
				employeeDesg.setCreatedBy(authentication.getName());
				employeeDesg.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeDesg.setModifiedBy(authentication.getName());
				employeeDesg.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				logger.info("EmployeeController::setEmployeeInfo::employeeDesg: {}", employeeDesg);
				model.addAttribute("empDesg", employeeDesg);
				model.addAttribute("designations", designationService.findAll());
			} else if (CCOAppliationConstants.GRADE.equals(requestType)) {
				EmployeeGrade employeeGrade = new EmployeeGrade();
				employeeGrade.setCreatedBy(authentication.getName());
				employeeGrade.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeGrade.setModifiedBy(authentication.getName());
				employeeGrade.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				logger.info("EmployeeController::setEmployeeInfo::employeeGrade: {}", employeeGrade);
				model.addAttribute("empGrade", employeeGrade);
				model.addAttribute("grades", gradeService.findAll());
			} else if (CCOAppliationConstants.COMPENSATION.equals(requestType)) {
				EmployeeCompensation employeeCompensation = new EmployeeCompensation();
				employeeCompensation.setCreatedBy(authentication.getName());
				employeeCompensation.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeCompensation.setModifiedBy(authentication.getName());
				employeeCompensation.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				List<EmployeeCompensation> employeeCompensations = new ArrayList<>();
				employeeCompensations.add(new EmployeeCompensation());
				employeeCompensation.setEmployeeCompensations(employeeCompensations);
				logger.info("EmployeeController::setEmployeeInfo::employeeCompensation: {}", employeeCompensation);
				model.addAttribute("empComp", employeeCompensation);
			} else if (CCOAppliationConstants.OFF_BOARD.equals(requestType)) {
				EmployeeOffBoading employeeOffBoarding = new EmployeeOffBoading();
				employeeOffBoarding.setCreatedBy(authentication.getName());
				employeeOffBoarding.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				employeeOffBoarding.setModifiedBy(authentication.getName());
				employeeOffBoarding.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				logger.info("EmployeeController::setEmployeeInfo::employeeOffBoarding: {}", employeeOffBoarding);
				model.addAttribute("empOffBoarding", employeeOffBoarding);
			}
			model.addAttribute("status", EmployeeStatusEnum.getAllStatus());
			model.addAttribute("requestType", requestType);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("EmployeeController::setEmployeeInfo::end ");
	}
}
