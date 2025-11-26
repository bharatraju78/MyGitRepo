package com.vam.cco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vam.cco.dao.entity.Employee;
import com.vam.cco.dao.entity.EmployeeCompensation;
import com.vam.cco.dao.entity.EmployeeDesignation;
import com.vam.cco.dao.entity.EmployeeGrade;
import com.vam.cco.dao.entity.EmployeeOffBoading;
import com.vam.cco.model.EmployeeModel;

public interface EmployeeService {
	EmployeeModel save(EmployeeModel employeeModel);

	Optional<EmployeeModel> findById(Long id);

	List<EmployeeModel> findAll();

	Page<EmployeeModel> findAll(Pageable pageable);

	EmployeeModel update(Long id, EmployeeModel employeeModel);

	void deleteById(Long id) throws Exception;
	
	EmployeeDesignation saveEmployeeDesignation(EmployeeDesignation employeeDesignation);
	
	EmployeeGrade saveEmployeeGrade(EmployeeGrade employeeGrade);
	
	EmployeeCompensation saveEmployeeCompensation(EmployeeCompensation employeeCompensation);
	
	EmployeeOffBoading saveEmployeeOffBoading(EmployeeOffBoading employeeOffBoading);
	
	List<EmployeeDesignation> findEmployeeDesignationById(Long id);
	
	List<EmployeeGrade> findEmployeeGradeById(Long id);
	
	List<EmployeeCompensation> findEmployeeCompensationById(Long id);
	
	List<EmployeeOffBoading> findEmployeeOffBoadingById(Long id);
	
	List<EmployeeModel> findEmployeesByProjectId(Long projectId);
	
	Employee convertToEntity(EmployeeModel employeeModel);
	
	Employee findByName(String name);
	
	List<EmployeeModel> findByDesignationName(String designationName);
	
	List<EmployeeModel> findByDesignationName(List<String> designationNames);
	
	List<EmployeeDesignation> findEmpDesgHis(Long employeeId);
	
	List<EmployeeGrade> findEmpGradeHis(Long employeeId);

	List<EmployeeCompensation> findEmpCompHis(Long id);
	
	List<EmployeeOffBoading> findEmpOffBoardingHis(Long id);
	
	List<String> findLeadersAndAssociates(List<String> designationNames);

	List<EmployeeModel> findEmployeesByProjectId(Long projectId, List<Long> projectRoleIds);
	
	List<EmployeeModel> findByProjectId(Long projectId);
	
}