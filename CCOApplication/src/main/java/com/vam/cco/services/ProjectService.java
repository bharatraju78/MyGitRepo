package com.vam.cco.services;

import com.vam.cco.dao.entity.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
	
	Project save(Project project);

	Optional<Project> findById(Long id);

	List<Project> findAll();

	Project update(Project project);

	void deleteById(Long id);

	List<Project> findByAccountId(Long accountId);

	Page<Project> findAll(Pageable pageable);
}