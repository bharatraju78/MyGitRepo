package com.vam.cco.services.impl;

import com.vam.cco.dao.entity.EmployeeGrade;
import com.vam.cco.dao.repository.EmployeeGradeRepository;
import com.vam.cco.services.EmployeeGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeGradeServiceImpl implements EmployeeGradeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeGradeServiceImpl.class);
    private final EmployeeGradeRepository repository;

    @Autowired
    public EmployeeGradeServiceImpl(EmployeeGradeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeGrade save(EmployeeGrade employeeGrade) {
        logger.info("Saving EmployeeGrade: {}", employeeGrade);
        return repository.save(employeeGrade);
    }

    @Override
    public Optional<EmployeeGrade> findById(Long id) {
        logger.info("Finding EmployeeGrade by id: {}", id);
        return repository.findById(id);
    }

    @Override
    public List<EmployeeGrade> findAll() {
        logger.info("Fetching all EmployeeGrades");
        return repository.findAll();
    }

    @Override
    public EmployeeGrade update(Long id, EmployeeGrade employeeGrade) {
        logger.info("Updating EmployeeGrade with id: {}", id);
        employeeGrade.setId(id);
        return repository.save(employeeGrade);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting EmployeeGrade with id: {}", id);
        repository.deleteById(id);
    }
}
