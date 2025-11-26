package com.vam.cco.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vam.cco.dao.entity.Role;
import com.vam.cco.dao.entity.User;
import com.vam.cco.model.AdminUser;
import com.vam.cco.services.RoleService;
import com.vam.cco.services.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;
    
    private final RoleService roleService;
    
    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    
    @GetMapping("/showUsers")
    public String showUsers(Model model) {
        logger.info("UserController::showUsers::start");
        List<AdminUser> users = userService.findAll();
        model.addAttribute("users", users);
        logger.info("UserController::showUsers::end");
		return "admin/userList";
	}

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        logger.info("UserController::showAddUserForm::start");
        AdminUser user = new AdminUser();
        model.addAttribute("user", user);
        List<Role> rolesList = roleService.findAll();
        logger.info("UserController::showAddUserForm::rolesList = {}", rolesList);
        model.addAttribute("roles", rolesList);
        logger.info("UserController::showAddUserForm::end");
        return "admin/userForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        logger.info("UserController::showEditUserForm::start");
        AdminUser user = userService.findByUserId(id);
        user.setPassword("");
        logger.info("UserController::showAddUserForm::user = {}", user);
        model.addAttribute("user", user);
        List<Role> rolesList = roleService.findAll();
        logger.info("UserController::showAddUserForm::rolesList = {}", rolesList);
        model.addAttribute("roles", rolesList);
        logger.info("UserController::showEditUserForm::end");
        return "admin/userForm";
    }

    @PostMapping("/user-save")
    public String saveUser(AdminUser adminUser) {
        logger.info("UserController::saveUser::start");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        
        User userEntity = new User();
		userEntity.setEnabled(Boolean.TRUE);
		userEntity.setId(adminUser.getId());
		userEntity.setPassword(bCryptPasswordEncoder.encode(adminUser.getPassword()));
		userEntity.setUsername(adminUser.getUsername());
		HashSet<Role> roleSet = new HashSet<Role>();
		Role role = roleService.findById(adminUser.getRoleId());
		roleSet.add(role);
		Set<User> userSet = new HashSet<User>();
		userSet.add(userEntity);
		userEntity.setRoles(roleSet);
		role.setUsers(userSet);
		
		userService.save(userEntity);
		
        logger.info("UserController::saveUser::end");
        return "redirect:/admin/users/user-list";
    }

    @GetMapping("/user-list")
    public String userList(Model model) {
        logger.info("UserController::userList::start");
        List<AdminUser> users = userService.findAll();
        model.addAttribute("users", users);
        logger.info("UserController::userList::end");
        return "admin/userList";
    }
    
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteById(id);
        return "redirect:/admin/users/user-list";
    }
    
    public static void main(String[] args) {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	String password = bCryptPasswordEncoder.encode("Pass1234");
    	System.out.println("password: "+password);
	}
}