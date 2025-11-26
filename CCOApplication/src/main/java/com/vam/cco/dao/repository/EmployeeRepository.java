package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vam.cco.dao.entity.Employee;
import com.vam.cco.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("SELECT new com.vam.cco.model.EmployeeModel(e.employeeId, e.vamId, e.name, e.doj, "
			+ "ea.startDate, ea.endDate, ea.status, ea.empAllId ) FROM Employee e "
			+ "join EmployeeAllocation ea on e.employeeId = ea.employee.employeeId "
			+ " WHERE ea.projectId = :projectId and ea.status = 'Active' order by e.name")
	List<EmployeeModel> findEmployeesByProjectId(Long projectId);
	
	Employee findByName(String name);
	
	@Query("select new com.vam.cco.model.EmployeeModel(e.name, e.password, pr.roleName) "
			+ "from Employee e "
			+ "join EmployeeAllocation ea on e.employeeId = ea.employee.employeeId "
			+ "join ProjectRole pr on ea.roleId = pr.projectRoleId "
			+ "where e.name = :name")
	EmployeeModel findByNameForLogin(@Param("name") String name);
	
	@Query("select DISTINCT new com.vam.cco.model.EmployeeModel(e.employeeId, e.name) from Employee e "
			+ "join EmployeeDesignation d on e.employeeId = d.employeeId "
			+ "join Designation des on d.designationId = des.designationId "
			+ "where des.designationName in ( :designationName ) order by e.name")
	List<EmployeeModel> findByDesignationName(@Param("designationName") String designationName);
	
	@Query("select DISTINCT new com.vam.cco.model.EmployeeModel(e.employeeId, e.name) from Employee e "
			+ "join EmployeeDesignation d on e.employeeId = d.employeeId "
			+ "join Designation des on d.designationId = des.designationId "
			+ "where des.designationName in (:designationNames) "
			+ "and e.employeeId not in (select ea.employee.employeeId from EmployeeAllocation ea where ea.status = 'Active') "
			+ "order by e.name")
	List<EmployeeModel> findByDesignationNames(@Param("designationNames") List<String> designationNames);
	
	@Query("SELECT new com.vam.cco.model.EmployeeModel(e.employeeId, e.vamId, e.name, e.doj, "
			+ "ea.startDate, ea.endDate, ea.status, ea.empAllId, pr.roleName ) FROM Employee e "
			+ "join EmployeeAllocation ea on e.employeeId = ea.employee.employeeId "
			+ "join ProjectRole pr on ea.roleId = pr.projectRoleId "
			+ " WHERE ea.projectId = :projectId and ea.status = 'Active' "
			+ " and ea.roleId in (:projectRoleIds) order by ea.roleId")
	List<EmployeeModel> findEmployeesByProjectId(Long projectId, List<Long> projectRoleIds);
	
	@Query("select DISTINCT new com.vam.cco.model.EmployeeModel(e.employeeId, e.name) from Employee e "
			+ "join EmployeeDesignation d on e.employeeId = d.employeeId "
			+ "join Designation des on d.designationId = des.designationId "
			+ "where des.designationName in (:designationNames) "
			+ "order by e.name")
	List<EmployeeModel> findByDesignationNamesForPortfolio(@Param("designationNames") List<String> designationNames);
	
	@Query("SELECT new com.vam.cco.model.EmployeeModel(e.employeeId, e.name) FROM "
			+ "Employee e  "
			+ "Join Portfolio p ON e.employeeId = p.portfolioHR or e.employeeId = p.portfolioHR1 "
			+ "Join Account a ON p.id = a.portfolio.id "
			+ "Join Project prj ON a.id = prj.account.id "
			+ "where prj.id = :projectId ")
	List<EmployeeModel> findByProjectId(@Param("projectId") Long employeeId);
}