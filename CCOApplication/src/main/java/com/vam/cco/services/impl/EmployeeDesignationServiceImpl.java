package com.vam.cco.services.impl;

import com.vam.cco.dao.entity.EmployeeDesignation;
import com.vam.cco.dao.repository.EmployeeDesignationRepository;
import com.vam.cco.services.EmployeeDesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDesignationServiceImpl implements EmployeeDesignationService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDesignationServiceImpl.class);
    private final EmployeeDesignationRepository repository;

    @Autowired
    public EmployeeDesignationServiceImpl(EmployeeDesignationRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeDesignation save(EmployeeDesignation employeeDesignation) {
        logger.info("Saving EmployeeDesignation: {}", employeeDesignation);
        return repository.save(employeeDesignation);
    }

    @Override
    public Optional<EmployeeDesignation> findById(Long id) {
        logger.info("Finding EmployeeDesignation by id: {}", id);
        return repository.findById(id);
    }

    @Override
    public List<EmployeeDesignation> findAll() {
        logger.info("Fetching all EmployeeDesignations");
        return repository.findAll();
    }

    @Override
    public EmployeeDesignation update(Long id, EmployeeDesignation employeeDesignation) {
        logger.info("Updating EmployeeDesignation with id: {}", id);
        employeeDesignation.setId(id);
        return repository.save(employeeDesignation);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting EmployeeDesignation with id: {}", id);
        repository.deleteById(id);
    }
}