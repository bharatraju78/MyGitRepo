package com.vam.cco.services;

import com.vam.cco.dao.entity.Department;
import com.vam.cco.model.DepartmentModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    Department save(Department department);
    Optional<Department> findById(Long id);
    List<Department> findAll();
    Department update(Department department);
    void deleteById(Long id);

    // Pageable variant used by controllers for entity lists
    Page<Department> findAll(Pageable pageable);

    // Pageable variant returning projection models
    Page<DepartmentModel> findAllDepartments(Pageable pageable);
}