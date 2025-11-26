package com.vam.cco.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.Employee;
import com.vam.cco.dao.entity.EmployeeAllocation;
import com.vam.cco.dao.entity.EmployeeDesignation;
import com.vam.cco.dao.entity.ProjectRole;
import com.vam.cco.dao.entity.Role;
import com.vam.cco.dao.entity.User;
import com.vam.cco.dao.repository.EmployeeAllocationRepository;
import com.vam.cco.dao.repository.EmployeeDesignationRepository;
import com.vam.cco.dao.repository.EmployeeRepository;
import com.vam.cco.dao.repository.ProjectRoleRepository;
import com.vam.cco.dao.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmployeeAllocationRepository allocationRepository;
    
    @Autowired
    private EmployeeDesignationRepository empDesignationRepository;
    
    @Autowired
    private ProjectRoleRepository projectRoleRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user by username: {}", username);
        try {
        	if(username.contains("?")) {
        		logger.info("Replacing special character in username: {}", username);
        		username = username.replace("?", "");
        	}
            User user = userRepository.searchByUsername(username);
            if (user != null) {
                String userName = user.getUsername();
                String password = user.getPassword();
                boolean enabled = user.isEnabled();
                Set<Role> roles = user.getRoles();
                String[] rolesArr = null;
                if (roles == null || roles.isEmpty()) {
                    logger.warn("User '{}' has no roles assigned.", userName);
                    rolesArr = new String[] {"USER"}; // Assign default role
                } else {
                    rolesArr = roles.stream().map(Role::getName).toArray(String[]::new);
                }
                logger.info("User '{}' loaded successfully with roles: {} , enable : {}", userName, (Object) rolesArr, enabled);
                return org.springframework.security.core.userdetails.User
                        .withUsername(userName)
                        .password(password)
                        .roles(rolesArr)
                        .disabled(!enabled)
                        .build();
            } else {
            	logger.info("User '{}' not found in user repository, checking employee repository.", username);
            	Employee employee = employeeRepository.findByName(username);
            	logger.info("employee '{}' not found in user repository, checking employee repository.", employee);
            	if(null != employee) {
            		Pageable pageable = PageRequest.of(0, 1);
            		List<EmployeeAllocation> allocations = allocationRepository.findByEmployeeId(employee.getEmployeeId(), pageable);
            		if(allocations != null && !allocations.isEmpty()) {
            			ProjectRole projectRole = projectRoleRepository.findById(allocations.get(0).getRoleId()).orElse(null);
            			if(null != projectRole) {
            				String roleName = projectRole.getRoleName();
            				roleName = roleName.toUpperCase().replace(" ", "_");
							String[] rolesArr = new String[] {roleName};
							Boolean enabled = Boolean.TRUE;
							logger.info("User '{}' loaded successfully with roles: {} , enable : {}", employee.getName(), (Object) rolesArr, enabled);
							return org.springframework.security.core.userdetails.User
									.withUsername(employee.getName())
									.password(employee.getPassword())
									.roles(rolesArr)
									.disabled(!enabled)
									.build();
            			}
					} else {
						logger.info("Employee '{}' has no allocation found, checking designation.", employee.getName());
						List<EmployeeDesignation> designations = empDesignationRepository.findByEmployeeId(employee.getEmployeeId(), pageable);
						logger.info("EmployeeDesignation------->{}", designations);
						if (designations != null && !designations.isEmpty()) {
							String[] rolesArr = new String[] {designations.get(0).getDesignationName()};
							Boolean enabled = Boolean.TRUE;
							logger.info("User '{}' loaded successfully with roles: {} , enable : {}", employee.getName(), (Object) rolesArr, enabled);
							logger.info("employee pwd = {}", employee.getPassword());	
							return org.springframework.security.core.userdetails.User
									.withUsername(employee.getName())
									.password(employee.getPassword())
									.roles(rolesArr)
									.disabled(!enabled)
									.build();
						}
					}
            	}
            	
                logger.error("User '{}' not found in database.", username);
                throw new UsernameNotFoundException("User not found");
            }
        } catch (Exception e) {
            logger.error("Exception occurred while loading user '{}': {}", username, e.getMessage(), e);
            throw new UsernameNotFoundException("User not found", e);
        }
    }
}