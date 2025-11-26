package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vam.cco.dao.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query("SELECT p FROM Project p WHERE p.account.id = :accountId")
	List<Project> findByAccountId(Long accountId);
}
