package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vam.cco.dao.entity.EmployeeCompensation;

@Repository
public interface EmployeeCompensationRepository extends JpaRepository<EmployeeCompensation, Long> {
	
	@Query("SELECT ec FROM EmployeeCompensation ec WHERE ec.employeeId = :employeeId "
			+ " and ec.status = 'Active' "
			+ "order by ec.startDate ")
	List<EmployeeCompensation> findByEmployeeId(Long employeeId, Pageable pageable);
	
	@Query("SELECT ec FROM EmployeeCompensation ec WHERE ec.employeeId = :employeeId "
			+ "order by ec.id desc")
	List<EmployeeCompensation> findEmpCompHis(Long employeeId);
}
