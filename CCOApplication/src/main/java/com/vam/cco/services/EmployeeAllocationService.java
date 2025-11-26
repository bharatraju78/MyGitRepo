package com.vam.cco.services;

import java.util.List;
import java.util.Optional;

import com.vam.cco.dao.entity.EmployeeAllocation;
import com.vam.cco.model.ExportModel;

public interface EmployeeAllocationService {
	EmployeeAllocation save(EmployeeAllocation employeeAllocation);

	Optional<EmployeeAllocation> findById(Long id);

	List<EmployeeAllocation> findAll();

	EmployeeAllocation update(EmployeeAllocation employeeAllocation);

	void deleteById(Long id);
	
	Boolean saveAll(List<EmployeeAllocation> employeeAllocations) throws Exception;
	
	List<EmployeeAllocation> findByAccountIdAndProjectId(Long accountId, Long projectId) throws Exception;
	
	List<EmployeeAllocation> findEmpAllToEdit(Long accountId, Long projectId, Long employeeId) throws Exception;
	
	List<ExportModel> exportByAccountId(Long accountId);
	
	public List<ExportModel> exportByAccountAndProjectId(Long accountId, List<Long> projectIds);
	
	public List<ExportModel> exportByAccountId(Long accountId, List<Long> projectRoleIds);
}
