package com.vam.cco.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vam.cco.dao.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	
}
