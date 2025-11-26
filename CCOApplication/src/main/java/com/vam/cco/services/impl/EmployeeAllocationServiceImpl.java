package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.EmployeeAllocation;
import com.vam.cco.dao.repository.EmployeeAllocationRepository;
import com.vam.cco.model.ExportModel;
import com.vam.cco.services.EmployeeAllocationService;

@Service
public class EmployeeAllocationServiceImpl implements EmployeeAllocationService {
	
	private Logger logger = LoggerFactory.getLogger(EmployeeAllocationServiceImpl.class);
	
    private final EmployeeAllocationRepository employeeAllocationRepository;

    @Autowired
    public EmployeeAllocationServiceImpl(EmployeeAllocationRepository employeeAllocationRepository) {
        this.employeeAllocationRepository = employeeAllocationRepository;
    }

    @Override
    public EmployeeAllocation save(EmployeeAllocation employeeAllocation) {
        return employeeAllocationRepository.save(employeeAllocation);
    }

    @Override
    public Optional<EmployeeAllocation> findById(Long id) {
        return employeeAllocationRepository.findById(id);
    }

    @Override
    public List<EmployeeAllocation> findAll() {
        return employeeAllocationRepository.findAll();
    }

    @Override
    public EmployeeAllocation update(EmployeeAllocation employeeAllocation) {
        return employeeAllocationRepository.save(employeeAllocation);
    }

    @Override
    public void deleteById(Long id) {
        employeeAllocationRepository.deleteById(id);
    }
    
    @Override
    public Boolean saveAll(List<EmployeeAllocation> employeeAllocations) throws Exception {
    	Boolean isSaved = Boolean.FALSE;
    	try {
			employeeAllocationRepository.saveAll(employeeAllocations);
		} catch (Exception e) {
			throw new Exception("Error while saving employee allocations: " + e.getMessage(), e);
		}
    	isSaved = Boolean.TRUE;
    	return isSaved;
    }
    
    @Override
    public List<EmployeeAllocation> findByAccountIdAndProjectId(Long accountId, Long projectId) throws Exception {
    	List<EmployeeAllocation> employeeAllocations = employeeAllocationRepository
				.findByAccountIdAndProjectId(accountId, projectId, null);
		logger.info("EmployeeAllocationServiceImpl::findByAccountIdAndProjectId::employeeAllocations: {}", employeeAllocations);
    	return employeeAllocations;
    }
    
    @Override
    public List<EmployeeAllocation> findEmpAllToEdit(Long accountId, Long projectId, Long employeeId) throws Exception {
    	logger.info("EmployeeAllocationServiceImpl::findEmpAllToEdit::start");
		List<EmployeeAllocation> employeeAllocations = employeeAllocationRepository
				.findByAccountIdAndProjectIdAndEmpAllId(accountId, projectId, employeeId, null);
		logger.info("EmployeeAllocationServiceImpl::findEmpAllToEdit::employeeAllocations: {}", employeeAllocations);
		return employeeAllocations;
    }
    
    @Override
	public List<ExportModel> exportByAccountId(Long accountId) {
		logger.info("EmployeeAllocationServiceImpl::exportByAccountId::start {}", accountId);
		List<ExportModel> exportModels = employeeAllocationRepository.exportByAccountId(accountId);
		logger.info("EmployeeAllocationServiceImpl::exportByAccountId::exportModels = {} ", exportModels);
		logger.info("EmployeeAllocationServiceImpl::exportByAccountId::end");
		return exportModels;
	}
    
    @Override
	public List<ExportModel> exportByAccountId(Long accountId, List<Long> projectRoleIds) {
		logger.info("EmployeeAllocationServiceImpl::exportByAccountId::start {}", accountId);
		List<ExportModel> exportModels = employeeAllocationRepository.exportByAccountId(accountId, projectRoleIds);
		logger.info("EmployeeAllocationServiceImpl::exportByAccountId::exportModels = {} ", exportModels);
		logger.info("EmployeeAllocationServiceImpl::exportByAccountId::end");
		return exportModels;
	}
    
    @Override
	public List<ExportModel> exportByAccountAndProjectId(Long accountId, List<Long> projectIds) {
		logger.info("EmployeeAllocationServiceImpl::exportByAccountAndProjectId::start accountId = {}, projectIds = {}", accountId, projectIds);
		List<ExportModel> exportModels = employeeAllocationRepository.exportByAccountAndProjectId(accountId, projectIds);
		logger.info("EmployeeAllocationServiceImpl::exportByAccountAndProjectId::exportModels = {} ", exportModels);
		logger.info("EmployeeAllocationServiceImpl::exportByAccountAndProjectId::end");
		return exportModels;
	}
}
