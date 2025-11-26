package com.vam.cco.services.impl;

import com.vam.cco.dao.entity.EmployeeCompensation;
import com.vam.cco.dao.repository.EmployeeCompensationRepository;
import com.vam.cco.services.EmployeeCompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeCompensationServiceImpl implements EmployeeCompensationService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeCompensationServiceImpl.class);
    private final EmployeeCompensationRepository repository;

    @Autowired
    public EmployeeCompensationServiceImpl(EmployeeCompensationRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeCompensation save(EmployeeCompensation employeeCompensation) {
        logger.info("Saving EmployeeCompensation: {}", employeeCompensation);
        return repository.save(employeeCompensation);
    }

    @Override
    public Optional<EmployeeCompensation> findById(Long id) {
        logger.info("Finding EmployeeCompensation by id: {}", id);
        return repository.findById(id);
    }

    @Override
    public List<EmployeeCompensation> findAll() {
        logger.info("Fetching all EmployeeCompensations");
        return repository.findAll();
    }

    @Override
    public EmployeeCompensation update(Long id, EmployeeCompensation employeeCompensation) {
        logger.info("Updating EmployeeCompensation with id: {}", id);
        employeeCompensation.setId(id);
        return repository.save(employeeCompensation);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting EmployeeCompensation with id: {}", id);
        repository.deleteById(id);
    }
}
