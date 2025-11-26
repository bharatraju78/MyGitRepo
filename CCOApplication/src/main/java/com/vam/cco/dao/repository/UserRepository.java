package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vam.cco.dao.entity.User;
import com.vam.cco.model.AdminUser;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User searchByUsername(String username);
	
	@Query("SELECT new com.vam.cco.model.AdminUser(u.id, u.username, u.password, r.name, r.id) "
			+ "FROM User u JOIN u.roles r order by u.username")
	List<AdminUser> findAllUser();
	
	@Query("SELECT new com.vam.cco.model.AdminUser(u.id, u.username, u.password, r.name, r.id) "
			+ "FROM User u JOIN u.roles r WHERE u.id = :id")
	AdminUser findByUserId(@Param("id") Long id);
	
}
