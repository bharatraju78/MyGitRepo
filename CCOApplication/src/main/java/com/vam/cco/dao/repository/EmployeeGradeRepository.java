package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vam.cco.dao.entity.EmployeeGrade;

@Repository
public interface EmployeeGradeRepository extends JpaRepository<EmployeeGrade, Long> {
    // Custom query methods can be added here if needed
	
	@Query("SELECT eg FROM EmployeeGrade eg WHERE eg.employeeId = :employeeId "
			+ "order by eg.id desc")
	List<EmployeeGrade> findByEmployeeId(Long employeeId, Pageable pageable);
	
		@Query("SELECT new com.vam.cco.dao.entity.EmployeeGrade(eg.employeeId, eg.gradeId, eg.startDate, eg.endDate,"
				+ "eg.comments, g.gradeName)  FROM EmployeeGrade eg  "
				+ "join Grade g on eg.gradeId = g.gradeId "
				+ "WHERE eg.employeeId = :employeeId "
			+ "order by eg.id desc")
	List<EmployeeGrade> findEmpGradeHis(Long employeeId);
}
