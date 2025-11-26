package com.vam.cco.controller;

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

import com.vam.cco.dao.entity.EmpUpdateRequest;
import com.vam.cco.dao.entity.Employee;
import com.vam.cco.dao.entity.Project;
import com.vam.cco.dao.service.EmpUpdateRequestService;
import com.vam.cco.model.EmployeeModel;
import com.vam.cco.services.EmployeeService;
import com.vam.cco.services.ProjectService;
import com.vam.cco.util.CCOAppliationConstants;
import com.vam.cco.util.IdGenerator;
import com.vam.cco.util.WorkItemStatus;


@Controller
@RequestMapping("/admin/empUpdateRequest")
public class EmpUpdateRequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpUpdateRequestController.class);

    @Autowired
    private EmpUpdateRequestService empUpdateRequestService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private IdGenerator idGenerator;
    
    @Autowired
    private ProjectService projectService;

    @GetMapping("/list")
    public String listEmpUpdateRequests(Model model, HttpSession session,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false, name = "successMessage") String successMessage,
        @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        logger.info("EmpUpdateRequestController::listEmpUpdateRequests::Start::");
        Long id = session.getAttribute("employeeId") != null ? (Long) session.getAttribute("employeeId") : 0L;
        logger.info("EmpUpdateRequestController::listEmpUpdateRequests::id = {}", id);
        String userRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        logger.info("EmpUpdateRequestController::listEmpUpdateRequests::userRole = {}", userRole);
        if (0L != id) {
            List<String> statusList = WorkItemStatus.getAllStatus();
            statusList.forEach(status -> {
                Page<EmpUpdateRequest> empUpdateRequests;
                Pageable pageable = PageRequest.of(page - 1, size);
                if ("ROLE_HR".equals(userRole.trim()) || "[ROLE_HR]".equals(userRole.trim())) {
                    empUpdateRequests = empUpdateRequestService.findByStatusReqTo(id, status, pageable);
                } else {
                    empUpdateRequests = empUpdateRequestService.findByStatusReqBy(id, status, pageable);
                }
                logger.info("EmpUpdateRequestController::listEmpUpdateRequests::empUpdateRequests = {}", empUpdateRequests.getContent());
                model.addAttribute("eur_" + status.toLowerCase(), empUpdateRequests.getContent());
                model.addAttribute("eur_count_" + status.toLowerCase(), empUpdateRequests.getTotalElements());
                if(status.equalsIgnoreCase(WorkItemStatus.COMPLETED.toString())) {
                	model.addAttribute("page", page);
                    model.addAttribute("size", size);
                    model.addAttribute("totalPages", empUpdateRequests.getTotalPages());
				} 
            });
        }
        if (null != successMessage) {
            model.addAttribute("successMessage", successMessage);
        }
        if (null != errorMessage) {
            model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("EmpUpdateRequestController::listEmpUpdateRequests::end");
        return "users/empUpdateRequestList";
    }
    
    @GetMapping("/showUserRequestsBy/{id}")
    public String showUserRequests(@PathVariable Long id,@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size, 
            @RequestParam(required = false, name = "fromPage") String fromPage, Model model) {
    	logger.info("EmpUpdateRequestController::showUserRequestsTo::Start::id: {}", id);
    	if(null == fromPage || "".equals(fromPage)) {
		List<String> statusList = WorkItemStatus.getAllStatus();
		statusList.stream().forEach(status -> {
			setUserRequestsBy(id, page, size, model, status);
		});
    	} else if("completed".equals(fromPage)) {
    		setUserRequestsBy(id, page, size, model, fromPage);
    		model.addAttribute("fromPage", fromPage);
    	}
        logger.info("EmpUpdateRequestController::listEmpUpdateRequests::end");
		return "users/empUpdateRequestList";
	}

    @GetMapping("/showUserRequestsTo/{id}")
    public String showUserRequestsTo(@PathVariable Long id,@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, name = "fromPage") String fromPage, Model model) {
    	logger.info("EmpUpdateRequestController::showUserRequestsTo::Start::id: {}", id);
    	if(null == fromPage || "".equals(fromPage)) {
			List<String> statusList = WorkItemStatus.getAllStatus();
			statusList.stream().forEach(status -> {
				setUserRequestTo(id, page, size, model, status);
			});
		} else if("completed".equals(fromPage)) {
			setUserRequestTo(id, page, size, model, fromPage);
			model.addAttribute("fromPage", fromPage);
		}
        logger.info("EmpUpdateRequestController::listEmpUpdateRequests::end");
		return "users/empUpdateRequestList";
	}

    @GetMapping({"/show/{employeeId}/{projectId}"})
    public String showForm(@PathVariable Long employeeId, @PathVariable Long projectId, Model model) {
    	
    	EmployeeModel employee = employeeService.findById(employeeId).orElse(null);
        EmpUpdateRequest empUpdateRequest = new EmpUpdateRequest();
        
        empUpdateRequest.setEmployeeId(employeeId);
        empUpdateRequest.setEmployeeName(employee.getName());
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if(userRole.contains("ROLE_ADMIN")) {
			empUpdateRequest.setRequestById(1L);
			empUpdateRequest.setRequestByName(authentication.getName());
		} else {
			 Employee requestBy = employeeService.findByName(authentication.getName());
			 empUpdateRequest.setRequestById(requestBy.getEmployeeId());
		     empUpdateRequest.setRequestByName(requestBy.getName());
		}
        model.addAttribute("projectId", projectId);
        model.addAttribute("actionFrom", "proEmpView");
        model.addAttribute("employees", employeeService.findByProjectId(projectId));
        model.addAttribute("status", WorkItemStatus.getAllStatus());
        model.addAttribute("empUpdateRequest", empUpdateRequest);
        return "users/empUpdateRequestForm";
    }
    
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
		EmpUpdateRequest empUpdateRequest = empUpdateRequestService.findById(id).orElse(null);
		if (empUpdateRequest == null) {
			return "redirect:/admin/empUpdateRequest/list";
		}
		
		EmployeeModel employee = employeeService.findById(empUpdateRequest.getEmployeeId()).orElse(null);
		empUpdateRequest.setEmployeeName(employee.getName());
		empUpdateRequest.setEmployeeId(employee.getEmployeeId());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		if (userRole.contains("ROLE_ADMIN")) {
			empUpdateRequest.setRequestById(1L);
			empUpdateRequest.setRequestByName(authentication.getName());
		} else {
			Employee requestBy = employeeService.findByName(authentication.getName());
			empUpdateRequest.setRequestById(requestBy.getEmployeeId());
			empUpdateRequest.setRequestByName(requestBy.getName());
		}
		
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("status", WorkItemStatus.getAllStatus());
		model.addAttribute("empUpdateRequest", empUpdateRequest);
		return "users/empUpdateRequestForm";
	}

    @PostMapping("/add")
    public String saveOrUpdate(@ModelAttribute EmpUpdateRequest empUpdateRequest, Model model) {
    	logger.info("EmpUpdateRequestController::saveOrUpdate::Start::empUpdateRequest: {}", empUpdateRequest);
    	try {
    		Long id = empUpdateRequest.getId();
			empUpdateRequest.setRequestNumber(idGenerator.generateAccountId());
			empUpdateRequest.setStatus(WorkItemStatus.CREATED.toString());
			empUpdateRequest.setStartDate(Calendar.getInstance().getTime());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empUpdateRequest.setCreatedBy(authentication.getName());
			empUpdateRequest.setCreatedAt(Calendar.getInstance().getTime());
			empUpdateRequest.setUpdatedBy(authentication.getName());
			empUpdateRequest.setUpdatedAt(Calendar.getInstance().getTime());
			empUpdateRequestService.save(empUpdateRequest);
			if(null == id) {
				model.addAttribute("successMessage", "Employee Update Request created successfully.");
			} else {
				model.addAttribute("successMessage", "Employee Update Request updated successfully.");
			}
		} catch (Exception e) {
			logger.error("EmpUpdateRequestController::saveOrUpdate::Error::", e);
			model.addAttribute("errorMessage", "Error occurred while processing the request. Please try again.");
		}
        logger.info("EmpUpdateRequestController::saveOrUpdate::End::empUpdateRequest: {}", empUpdateRequest);
        String actionFrom = empUpdateRequest.getActionFrom();
        if ("proEmpView".equals(actionFrom)) {
			return "redirect:/admin/employee-allocation/employee-list/" + empUpdateRequest.getProjectId();
		}
        return "redirect:/admin/empUpdateRequest/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        empUpdateRequestService.deleteById(id);
        return "redirect:/admin/empUpdateRequest/list";
    }
    
    @GetMapping("/requestToAction/{id}/{employeeId}")
    public String requestToAction(@PathVariable Long id, @PathVariable Long employeeId, Model model) {
    	logger.info("EmpUpdateRequestController::requestToAction::Start::id: {}, employeeId: {}", id, employeeId);
		EmpUpdateRequest empUpdateRequest = empUpdateRequestService.findById(id).orElse(null);
		if (empUpdateRequest != null) {
			empUpdateRequest.setStatus(WorkItemStatus.IN_PROGRESS.toString());
			empUpdateRequest.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			empUpdateRequest.setUpdatedAt(Calendar.getInstance().getTime());
			empUpdateRequestService.update(empUpdateRequest);
		}
		logger.info("EmpUpdateRequestController::requestToAction::End::id: {}, employeeId: {}", id, employeeId);
		return "redirect:/admin/employees/editHR/" + employeeId +"" + "/" + id + "/" + empUpdateRequest.getRequestType();
    }
    
    private void setUserRequestTo(Long id, int page, int size, Model model, String status) {
		logger.info("EmpUpdateRequestController::setUserRequestTo::start::id: {}, page: {}, size: {}, status: {}", id, page, size, status);
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<EmpUpdateRequest> empUpdateRequests = empUpdateRequestService.findByStatusReqTo(id, status, pageable);
		model.addAttribute("eur_" + status.toLowerCase(), empUpdateRequests.getContent());
		model.addAttribute("eur_count_" + status.toLowerCase(), empUpdateRequests.getContent().size());
		if(status.equalsIgnoreCase(WorkItemStatus.COMPLETED.toString())) {
			model.addAttribute("page", page);
		    model.addAttribute("size", size);
		    model.addAttribute("totalPages", empUpdateRequests.getTotalPages());
		}
		logger.info("EmpUpdateRequestController::setUserRequestTo::end");
	}
    
    private void setUserRequestsBy(Long id, int page, int size, Model model, String status) {
		logger.info("EmpUpdateRequestController::setUserRequestsBy::start::id: {}, page: {}, size: {}, status: {}", id, page, size, status);
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<EmpUpdateRequest> empUpdateRequests = empUpdateRequestService.findByStatusReqBy(id, status, pageable);
		model.addAttribute("eur_" + status.toLowerCase(), empUpdateRequests.getContent());
		model.addAttribute("eur_count_" + status.toLowerCase(), empUpdateRequests.getContent().size());
		model.addAttribute("id", id);
		if(status.equalsIgnoreCase(WorkItemStatus.COMPLETED.toString())) {
			model.addAttribute("page", page);
			model.addAttribute("size", size);
			model.addAttribute("totalPages", empUpdateRequests.getTotalPages());
		}
		logger.info("EmpUpdateRequestController::setUserRequestsBy::end");
	}
}