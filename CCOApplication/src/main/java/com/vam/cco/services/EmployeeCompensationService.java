package com.vam.cco.services;

import com.vam.cco.dao.entity.EmployeeCompensation;
import java.util.List;
import java.util.Optional;

public interface EmployeeCompensationService {
    EmployeeCompensation save(EmployeeCompensation employeeCompensation);
    Optional<EmployeeCompensation> findById(Long id);
    List<EmployeeCompensation> findAll();
    EmployeeCompensation update(Long id, EmployeeCompensation employeeCompensation);
    void deleteById(Long id);
}
