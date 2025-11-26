package com.vam.cco.controller;

import com.vam.cco.dao.entity.Role;
import com.vam.cco.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {
	
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role-list")
    public String listRoles(Model model) {
        logger.info("Fetching all roles");
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        logger.info("Total roles found: {}", roles.size());
        return "admin/roleList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add role form");
        model.addAttribute("role", new Role());
        return "admin/roleForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for role id: {}", id);
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "admin/roleForm";
    }

    @PostMapping("/role-save")
    public String saveRole(@ModelAttribute Role role) {
        logger.info("Saving role: {}", role.getName());
        roleService.save(role);
        logger.info("Role saved successfully");
        return "redirect:/admin/roles/role-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        logger.info("Deleting role with id: {}", id);
        roleService.deleteById(id);
        logger.info("Role deleted successfully");
        return "redirect:/admin/roles/role-list";
    }
}