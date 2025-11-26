package com.vam.cco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vam.cco.dao.entity.ProjectRole;

public interface ProjectRoleService {
	ProjectRole save(ProjectRole projectRole);

	Optional<ProjectRole> findById(Long id);

	List<ProjectRole> findAll();

	Page<ProjectRole> findAll(Pageable pageable);

	ProjectRole update(ProjectRole projectRole);

	void deleteById(Long id);
	
	List<Long> findLeadsAndAssociates(List<String> roleNames);
	
	List<ProjectRole> excludeLeaderShip(List<String> roleNames);
}