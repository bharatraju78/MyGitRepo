package com.vam.cco.services.impl;

import com.vam.cco.dao.entity.ProjectRole;
import com.vam.cco.dao.repository.ProjectRoleRepository;
import com.vam.cco.services.ProjectRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectRoleServiceImpl implements ProjectRoleService {
	
    private final ProjectRoleRepository projectRoleRepository;

    @Autowired
    public ProjectRoleServiceImpl(ProjectRoleRepository projectRoleRepository) {
        this.projectRoleRepository = projectRoleRepository;
    }

    @Override
    public ProjectRole save(ProjectRole projectRole) {
        return projectRoleRepository.save(projectRole);
    }

    @Override
    public Optional<ProjectRole> findById(Long id) {
        return projectRoleRepository.findById(id);
    }

    @Override
    public List<ProjectRole> findAll() {
        return ((Streamable<ProjectRole>) projectRoleRepository.findAll()
            .stream()
            .sorted((r1, r2) -> r1.getRoleName().compareToIgnoreCase(r2.getRoleName()))).toList();
    }

    @Override
    public Page<ProjectRole> findAll(Pageable pageable) {
        return projectRoleRepository.findAllProjectRoles(pageable);
    }

    @Override
    public ProjectRole update(ProjectRole projectRole) {
        return projectRoleRepository.save(projectRole);
    }

    @Override
    public void deleteById(Long id) {
        projectRoleRepository.deleteById(id);
    }
    
    @Override
    public List<Long> findLeadsAndAssociates(List<String> roleNames) {
    	return projectRoleRepository.findLeadsAndAssociates(roleNames);
    }
    
    @Override
    public List<ProjectRole> excludeLeaderShip(List<String> roleNames) {
    	return projectRoleRepository.excludeLeaderShip(roleNames);
    }
}