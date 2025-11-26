package com.vam.cco.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vam.cco.dao.entity.Employee;
import com.vam.cco.model.Login;
import com.vam.cco.services.EmployeeService;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String welcome(Model model) {
		logger.info("LoginController::welcome::start");
		model.addAttribute("login", new Login());
		logger.info("LoginController::welcome::end");
		return "login";
	}
	
	@PostMapping("/login")
	public Boolean login(Model model, Login login, HttpServletRequest request) {
		logger.info("LoginController::login::start");
		Boolean returnForward = Boolean.FALSE;
		try {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", login.getUserName());
			session.setAttribute("loginUserType", login.getPassword());
			returnForward = Boolean.TRUE;
		} catch (Exception e) {
			logger.error("Error during login: {}", e.getMessage());
		}
		logger.info("LoginController::login::end");
		return returnForward;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "logout")
	public String submit(Model model, HttpServletRequest request) {
		String returnForward = "login";
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("loginUser");
			session.removeAttribute("loginUserType");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return returnForward;
	}
	
	@GetMapping(value = "/postLogin")
    public String postLogin(Model model, HttpSession session) {
		logger.info("LoginController::postLogin::start = {}", SecurityContextHolder.getContext().getAuthentication().getName());
		
		String userRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		userRole = userRole.replace("[", "").replace("]", "").trim();
		logger.info("LoginController::postLogin::userRole = {}", userRole);
		
		if(userRole.contains("ROLE_ADMIN") || userRole.contains("ROLE_POWER_USER")) {
			model.addAttribute("employeeId", 1L);
			session.setAttribute("employeeId", 1L);
		} else {
			 Employee emoloyee = employeeService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
			 model.addAttribute("employeeId", emoloyee.getEmployeeId());
			 session.setAttribute("employeeId", emoloyee.getEmployeeId());
		}
        return "dashboard";
    }
	
	@GetMapping(value = "/home")
    public String home(Model model, HttpSession session) {
		logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
		
		String userRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		userRole = userRole.replace("[", "").replace("]", "").trim();
		
        return "dashboard";
    }
	
	@GetMapping(value = "/loginFailed")
    public String loginError(Model model) {
		logger.info("LoginController::loginError::start");
        model.addAttribute("error", "true");
        logger.info(SecurityContextHolder.getContext().getAuthentication().toString());
        return "login";
    }
	
}
