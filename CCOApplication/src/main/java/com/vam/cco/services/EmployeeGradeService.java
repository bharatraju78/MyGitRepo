package com.vam.cco.services;

import com.vam.cco.dao.entity.EmployeeGrade;
import java.util.List;
import java.util.Optional;

public interface EmployeeGradeService {
    EmployeeGrade save(EmployeeGrade employeeGrade);
    Optional<EmployeeGrade> findById(Long id);
    List<EmployeeGrade> findAll();
    EmployeeGrade update(Long id, EmployeeGrade employeeGrade);
    void deleteById(Long id);
}
