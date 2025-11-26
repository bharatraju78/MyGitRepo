package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vam.cco.dao.entity.EmployeeAllocation;
import com.vam.cco.model.ExportModel;

public interface EmployeeAllocationRepository extends JpaRepository<EmployeeAllocation, Long> {
	
	@Query("SELECT ea FROM EmployeeAllocation ea WHERE ea.employee.employeeId = :employeeId ")
	List<EmployeeAllocation> findByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);
	
	@Query("SELECT new com.vam.cco.dao.entity.EmployeeAllocation(ea.empAllId, e.employeeId, e.name, pr.projectRoleId, pr.roleName) "
			+ "FROM EmployeeAllocation ea "
			+ "JOIN Employee e  on e.employeeId = ea.employee.employeeId "
			+ "JOIN ProjectRole pr on pr.projectRoleId = ea.roleId "
			+ "WHERE ea.accountId = :accountId AND ea.projectId = :projectId "
			+ "and ea.status = 'Active' order by e.name")
	List<EmployeeAllocation> findByAccountIdAndProjectId(@Param("accountId") Long accountId, 
			@Param("projectId") Long projectId, Pageable pageable);
	
	@Query("SELECT new com.vam.cco.dao.entity.EmployeeAllocation(ea.empAllId, e.employeeId, e.name, pr.projectRoleId, pr.roleName) "
			+ "FROM EmployeeAllocation ea "
			+ "JOIN Employee e  on e.employeeId = ea.employee.employeeId "
			+ "JOIN ProjectRole pr on pr.projectRoleId = ea.roleId "
			+ "WHERE ea.accountId = :accountId AND ea.projectId = :projectId "
			+ " and ea.empAllId = :empAllId "
			+ "and ea.status = 'Active' order by e.name")
	List<EmployeeAllocation> findByAccountIdAndProjectIdAndEmpAllId(@Param("accountId") Long accountId, 
			@Param("projectId") Long projectId,@Param("empAllId") Long empAllId, Pageable pageable);
	
	
	final String exportQuery = "SELECT  new com.vam.cco.model.ExportModel("
			+ " e.vamId, "
			+ "    e.name, "
			+ "    e.doj, "
			+ "    g.gradeName, "
			+ "    d.designationName, "
			+ "    pr.roleName, "
		    + "    v.verticalName, "
			+ "    a.accName, "
			+ "    p.name, "
			+ "    e.skillDataFromLD, "
			+ "    e.currentSkill, "
			+ "    e.status, "
			+ "    e.statusWithDays, "
			+ "    e.vamExp, "
			+ "    e.totalExp, "
			+ "    e.finalStatus, "
			+ "    e.resignationDate, "
			+ "    ea.performanceAsPerPM, "
			+ "    e.attritionRisk, "
			+ "    ea.ror, "
			+ "    ea.startDate, "
			+ "    ea.endDate, "
			+ "    ea.rorDate, "
			+ "    ea.rorComments, "
			+ "    ea.rorOnBoadingDate, "
			+ "    ec.ctc, "
			+ "    e.todo, "
			+ "    e.yearOneHike, "
			+ "    e.yearTwoHike, "
			+ "    ea.allocationPercentage, "
			+ "    ea.allocationType,"
			+ "	   pr.projectRoleId, e.employeeId, a.accountId, ea.empAllId, v.verticalId, ea.projectId,"
			+ "    ea.replName, ea.replVAMId, ec.startDate, ec.endDate ) "
			+ "FROM "
			+ "    Employee e "
			+ "      LEFT JOIN "
			+ "    EmployeeAllocation ea ON ea.employee.employeeId = e.employeeId "
			+ "        LEFT JOIN EmployeeGrade eg ON e.employeeId = eg.employeeId and eg.status = 'Active' "
			+ "    Join Grade g ON eg.gradeId = g.gradeId "
			+ "        LEFT JOIN EmployeeDesignation ed ON e.employeeId = ed.employeeId and ed.status = 'Active' "
			+ "    Join Designation d ON ed.designationId = d.designationId "
			+ "        JOIN "
			+ "    ProjectRole pr ON ea.roleId = pr.projectRoleId "
			+ "        LEFT JOIN EmployeeCompensation ec ON e.employeeId = ec.employeeId and ec.status = 'Active' "
			+ "        LEFT JOIN "
			+ "    Vertical v ON ea.verticalId = v.verticalId "
			+ "        JOIN "
			+ "    Account a ON ea.accountId = a.accountId "
			+ "        JOIN "
			+ "    Project p ON ea.projectId = p.id "
			+ "WHERE";
	@Query(value = exportQuery 
			+ "    ea.accountId = :accountId order by e.name")
	List<ExportModel> exportByAccountId(@Param("accountId") Long accountId);
	
	@Query(value = exportQuery 
			+ "    ea.accountId = :accountId "
			+ " and ea.roleId in (:projectRoleIds) order by ea.roleId, ec.id ")
	List<ExportModel> exportByAccountId(@Param("accountId") Long accountId, @Param("projectRoleIds") List<Long> projectRoleIds);
	
	@Query(value = exportQuery + "    ea.accountId = :accountId"
			+ " AND ea.projectId IN (:projectIds)  order by e.name")
	List<ExportModel> exportByAccountAndProjectId(@Param("accountId") Long accountId,
			@Param("projectIds") List<Long> projectIds);
}
