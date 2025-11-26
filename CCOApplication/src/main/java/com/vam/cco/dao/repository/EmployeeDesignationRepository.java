package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vam.cco.dao.entity.EmployeeDesignation;

@Repository
public interface EmployeeDesignationRepository extends JpaRepository<EmployeeDesignation, Long> {
	
	@Query("SELECT new com.vam.cco.dao.entity.EmployeeDesignation(d.designationName) "
			+ " FROM EmployeeDesignation ed "
			+ " join Designation d on ed.designationId = d.id "
			+ "WHERE ed.employeeId = :employeeId")
	List<EmployeeDesignation> findByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);
	
	@Query("SELECT ed FROM EmployeeDesignation ed WHERE ed.employeeId = :employeeId and ed.status = 'ACTIVE' "
			+ "order by ed.id desc ")
	List<EmployeeDesignation> findByEmpId(@Param("employeeId") Long employeeId, Pageable pageable);
	
	@Query("SELECT new com.vam.cco.dao.entity.EmployeeDesignation(ed.employeeId, ed.designationId, ed.startDate, ed.endDate, ed.comments, d.designationName) "
			+ "FROM EmployeeDesignation ed "
			+ "join Designation d on ed.designationId = d.designationId "
			+ "WHERE ed.employeeId = :employeeId "
			+ "order by ed.id desc ")
	List<EmployeeDesignation> findEmpDesgHis(@Param("employeeId") Long employeeId);
	
	@Query("SELECT d.designationName "
			+ "FROM EmployeeDesignation ed "
			+ "join Designation d on ed.designationId = d.designationId "
			+ "WHERE d.designationName not in (:designationNames) and ed.status = 'Active' "
			+ "order by ed.employeeId")
	List<String> findLeadersAndAssociates(@Param("designationNames") List<String> designationNames);
}
