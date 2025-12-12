package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vam.cco.dao.entity.Department;
import com.vam.cco.model.DepartmentModel;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT distinct new com.vam.cco.model.DepartmentModel(d.departmentId, d.departmentCode, d.departmentName, d.departmentStatus, d.createdBy, d.modifiedBy, d.createdDate, d.modifiedDate) FROM Department d order by d.departmentName")
    Page<DepartmentModel> findAllDepartments(Pageable pageable);

    @Query(value = "SELECT distinct new com.vam.cco.model.DepartmentModel(d.departmentId, d.departmentCode, d.departmentName, d.departmentStatus, d.createdBy, d.modifiedBy, d.createdDate, d.modifiedDate) FROM Department d WHERE d.departmentStatus = :status order by d.departmentName")
    Page<DepartmentModel> findDepartmentsByStatus(@Param("status") String status, Pageable pageable);

    @Query(value = "SELECT distinct new com.vam.cco.model.DepartmentModel(d.departmentId, d.departmentCode, d.departmentName, d.departmentStatus, d.createdBy, d.modifiedBy, d.createdDate, d.modifiedDate) FROM Department d WHERE d.departmentCode = :code")
    List<DepartmentModel> findByDepartmentCode(@Param("code") String code);

    // Find by primary key id and return projection
    @Query("SELECT new com.vam.cco.model.DepartmentModel(d.departmentId, d.departmentCode, d.departmentName, d.departmentStatus, d.createdBy, d.modifiedBy, d.createdDate, d.modifiedDate) FROM Department d WHERE d.departmentId = :id")
    DepartmentModel findByDepartmentId(@Param("id") Long id);

    // Find by department name (may return multiple matches)
    @Query("SELECT distinct new com.vam.cco.model.DepartmentModel(d.departmentId, d.departmentCode, d.departmentName, d.departmentStatus, d.createdBy, d.modifiedBy, d.createdDate, d.modifiedDate) FROM Department d WHERE d.departmentName = :name")
    List<DepartmentModel> findByDepartmentName(@Param("name") String name);
}