package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vam.cco.dao.entity.ProjectRole;

public interface ProjectRoleRepository extends JpaRepository<ProjectRole, Long> {
	
	@Query("SELECT pr FROM ProjectRole pr order by pr.roleName ASC")
	Page<ProjectRole> findAllProjectRoles(Pageable pageable);
	
	
	@Query("SELECT pr.projectRoleId FROM ProjectRole pr WHERE pr.roleName "
			+ "NOT IN :roleNames")
	List<Long> findLeadsAndAssociates(List<String> roleNames);
	
	@Query("SELECT pr FROM ProjectRole pr WHERE pr.roleName NOT IN :roleNames")
	List<ProjectRole> excludeLeaderShip(List<String> roleNames);
}
