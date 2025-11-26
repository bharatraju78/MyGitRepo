package com.vam.cco.services.impl;

import com.vam.cco.dao.entity.Project;
import com.vam.cco.dao.repository.ProjectRepository;
import com.vam.cco.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {
        logger.info("Saving project: {}", project);
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findById(Long id) {
        logger.info("Finding project by id: {}", id);
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> findAll() {
        logger.info("Finding all projects");
        return projectRepository.findAll();
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        logger.info("Finding all projects with pagination: {}", pageable);
        return projectRepository.findAll(pageable);
    }

    @Override
    public Project update(Project project) {
        logger.info("Updating project: {}", project);
        return projectRepository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting project by id: {}", id);
        projectRepository.deleteById(id);
    }
    
    @Override
    public List<Project> findByAccountId(Long accountId) {
        logger.info("Finding projects by accountId: {}", accountId);
        return projectRepository.findByAccountId(accountId);
    }
}