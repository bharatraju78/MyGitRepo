package com.vam.cco.services;

import com.vam.cco.dao.entity.EmployeeDesignation;
import java.util.List;
import java.util.Optional;

public interface EmployeeDesignationService {
    EmployeeDesignation save(EmployeeDesignation employeeDesignation);
    Optional<EmployeeDesignation> findById(Long id);
    List<EmployeeDesignation> findAll();
    EmployeeDesignation update(Long id, EmployeeDesignation employeeDesignation);
    void deleteById(Long id);
}
