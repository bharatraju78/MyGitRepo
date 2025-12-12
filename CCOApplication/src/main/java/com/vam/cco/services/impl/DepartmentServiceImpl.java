package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.Department;
import com.vam.cco.dao.repository.DepartmentRepository;
import com.vam.cco.model.DepartmentModel;
import com.vam.cco.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "departmentName"));
    }

    @Override
    public Department update(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public Page<DepartmentModel> findAllDepartments(Pageable pageable) {
        return departmentRepository.findAllDepartments(pageable);
    }
}