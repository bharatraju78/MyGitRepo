package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vam.cco.dao.entity.EmployeeOffBoading;

@Repository
public interface EmployeeOffBoadingRepository extends JpaRepository<EmployeeOffBoading, Long> {
    
	@Query("SELECT e FROM EmployeeOffBoading e WHERE e.employeeId = ?1 "
			+ " ORDER BY e.id DESC")
	List<EmployeeOffBoading> findByEmployeeId(Long employeeId, Pageable pageable);
		
	@Query("SELECT e FROM EmployeeOffBoading e WHERE e.employeeId = ?1 "
			+ " ORDER BY e.id")
	List<EmployeeOffBoading> findEmpOffBoardingHis(Long employeeId);
}
