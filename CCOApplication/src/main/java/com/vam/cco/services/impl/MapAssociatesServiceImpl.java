package com.vam.cco.services.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.Employee;
import com.vam.cco.dao.entity.EmployeeAllocation;
import com.vam.cco.model.EmpForm;
import com.vam.cco.model.EmployeeModel;
import com.vam.cco.model.MapAssociatesModel;
import com.vam.cco.services.EmployeeAllocationService;
import com.vam.cco.services.EmployeeService;
import com.vam.cco.services.MapAssociatesService;
import com.vam.cco.util.CCOAppliationConstants;

@Service
public class MapAssociatesServiceImpl implements MapAssociatesService {

	private Logger logger = LoggerFactory.getLogger(MapAssociatesServiceImpl.class);
	
	private final EmployeeService employeeService;
	
	private final EmployeeAllocationService employeeAllocationService;
	
	@Autowired
	public MapAssociatesServiceImpl(EmployeeService employeeService,
			EmployeeAllocationService employeeAllocationService) {
		this.employeeService = employeeService;
		this.employeeAllocationService = employeeAllocationService;
	}
	
	@Override
	public MapAssociatesModel saveMapAssociatesModel(MapAssociatesModel mapAssociatesModel) {
		logger.info("MapAssociatesServiceImpl::saveMapAssociatesModel::start");
		try {
			/*List<EmployeeAllocation> employeeAllocations = new ArrayList<>();
			 *
			 * createEmpAllEntity(mapAssociatesModel,mapAssociatesModel.getLeaderShipList(),
			 * employeeAllocations);
			 * createEmpAllEntity(mapAssociatesModel,mapAssociatesModel.getDirectorList(),
			 * employeeAllocations);
			 * createEmpAllEntity(mapAssociatesModel,mapAssociatesModel.getManagerList(),
			 * employeeAllocations); if(null != employeeAllocations &&
			 * !employeeAllocations.isEmpty()) {
			 * employeeAllocationService.saveAll(employeeAllocations); }
			 */
			EmployeeAllocation allocation = new EmployeeAllocation();
			allocation.setAccountId(mapAssociatesModel.getAccountId());
			allocation.setProjectId(mapAssociatesModel.getProjectId());
			allocation.setVerticalId(1L);
			allocation.setAllocationType(CCOAppliationConstants.FULL_ALLOCATION);
			allocation.setStatus(CCOAppliationConstants.EMP_STATUS_ACTIVE);
			allocation.setStartDate(Calendar.getInstance().getTime());
			allocation.setRoleId(mapAssociatesModel.getRoleId());
			allocation.setEmpAllId(mapAssociatesModel.getEmpAllId());
			EmployeeModel employee = employeeService.findById(mapAssociatesModel.getEmpId()).get();
			Employee emp = employeeService.convertToEntity(employee);
			allocation.setEmployee(emp);
			
			employeeAllocationService.save(allocation);
			
		} catch (Exception e) {
			throw new RuntimeException("Error while saving employee allocations: " + e.getMessage(), e);
		}
		logger.info("MapAssociatesServiceImpl::saveMapAssociatesModel::end");
		return mapAssociatesModel;
	}
	
	@Override
	public MapAssociatesModel fetchMappedAssociates(Long accountId, Long projectId) throws Exception {
		logger.info("MapAssociatesServiceImpl::fetchMappedAssociates::start");
		List<EmployeeAllocation> employeeAllocations = employeeAllocationService.
				findByAccountIdAndProjectId(accountId, projectId);
		MapAssociatesModel mapAssociatesModel = new MapAssociatesModel();
		mapAssociatesModel.setEmployeeAllocationList(employeeAllocations);
		if(null != employeeAllocations && !employeeAllocations.isEmpty()) {
			employeeAllocations.stream().forEach(allocation -> {
				
				if(CCOAppliationConstants.leaderShipList.contains(allocation.getRoleName().trim())) {
					allocation.setAssociateType(CCOAppliationConstants.ASSOCIATE_TYPE_LEADERSHIP);
				}
				if(CCOAppliationConstants.directorList.contains(allocation.getRoleName().trim())) {
					allocation.setAssociateType(CCOAppliationConstants.ASSOCIATE_TYPE_DIRECTOR);
				}
				if(CCOAppliationConstants.managerList.contains(allocation.getRoleName().trim())) {
					allocation.setAssociateType(CCOAppliationConstants.ASSOCIATE_TYPE_MANAGER);
				}
			});
		}
		/*
		 * MapAssociatesModel mapAssociatesModel = new MapAssociatesModel();
		 * mapAssociatesModel.setAccountId(accountId);
		 * mapAssociatesModel.setProjectId(projectId);
		 * mapAssociatesModel.setLeaderShipList(new ArrayList<>());
		 * mapAssociatesModel.setDirectorList(new ArrayList<>());
		 * mapAssociatesModel.setManagerList(new ArrayList<>()); if(null !=
		 * employeeAllocations && !employeeAllocations.isEmpty()) {
		 * employeeAllocations.stream().forEach(allocation -> {
		 * logger.info("=----empId---->{}", allocation.getEmpId());
		 * logger.info("=----empName---->{}", allocation.getEmpName());
		 * logger.info("=----roleId---->{}", allocation.getRoleId());
		 * logger.info("=----roleName---->{}", allocation.getRoleName());
		 * logger.info("=----leaderShipList---->{}",
		 * CCOAppliationConstants.leaderShipList.contains(allocation.getRoleName().trim(
		 * )));
		 * if(CCOAppliationConstants.leaderShipList.contains(allocation.getRoleName().
		 * trim())) { EmpForm empForm = new EmpForm();
		 * empForm.setEmpId(allocation.getEmpId());
		 * empForm.setEmpName(allocation.getEmpName());
		 * empForm.setRoleId(allocation.getRoleId());
		 * empForm.setRoleName(allocation.getRoleName());
		 * empForm.setEmpAllId(allocation.getEmpAllId());
		 * mapAssociatesModel.getLeaderShipList().add(empForm); }
		 * if(CCOAppliationConstants.directorList.contains(allocation.getRoleName())) {
		 * EmpForm empForm = new EmpForm(); empForm.setEmpId(allocation.getEmpId());
		 * empForm.setEmpName(allocation.getEmpName());
		 * empForm.setRoleId(allocation.getRoleId());
		 * empForm.setRoleName(allocation.getRoleName());
		 * empForm.setEmpAllId(allocation.getEmpAllId());
		 * mapAssociatesModel.getDirectorList().add(empForm); }
		 * if(CCOAppliationConstants.managerList.contains(allocation.getRoleName())) {
		 * EmpForm empForm = new EmpForm(); empForm.setEmpId(allocation.getEmpId());
		 * empForm.setEmpName(allocation.getEmpName());
		 * empForm.setRoleId(allocation.getRoleId());
		 * empForm.setRoleName(allocation.getRoleName());
		 * empForm.setEmpAllId(allocation.getEmpAllId());
		 * mapAssociatesModel.getManagerList().add(empForm); } }); }
		 */
		logger.info("MapAssociatesServiceImpl::fetchMappedAssociates::mapAssociatesModel::{}", mapAssociatesModel);
		logger.info("MapAssociatesServiceImpl::fetchMappedAssociates::end");
		return mapAssociatesModel;
	}

	@Override
	public void deleteMappedAssociates(Long empAllId) throws Exception {
		logger.info("MapAssociatesServiceImpl::deleteMappedAssociates::start");
		logger.info("MapAssociatesServiceImpl::deleteMappedAssociates::empAllId::{}", empAllId);
		EmployeeAllocation employeeAllocation = employeeAllocationService.findById(empAllId).orElse(null);
		if(null != employeeAllocation) {
			logger.info("MapAssociatesServiceImpl::deleteMappedAssociates::employeeAllocation::{}", employeeAllocation);
			employeeAllocation.setStatus(CCOAppliationConstants.EMP_STATUS_INACTIVE);
			employeeAllocation.setEndDate(Calendar.getInstance().getTime());
			employeeAllocationService.update(employeeAllocation);
		} else {
			logger.warn("MapAssociatesServiceImpl::deleteMappedAssociates::No allocation found for empAllId: {}", empAllId);
		}
		logger.info("MapAssociatesServiceImpl::deleteMappedAssociates::end");
	}
	
	@Override
	public MapAssociatesModel findEmpAllToEdit(MapAssociatesModel mapAssociatesModel) throws Exception {
		logger.info("MapAssociatesServiceImpl::updateMappedAssociates::start");
		logger.info("MapAssociatesServiceImpl::updateMappedAssociates::mapAssociatesModel::{}", mapAssociatesModel);
		List<EmployeeAllocation> employeeAllocations = employeeAllocationService.findEmpAllToEdit(mapAssociatesModel.getAccountId(),
				mapAssociatesModel.getProjectId(), mapAssociatesModel.getEmpAllId());
		mapAssociatesModel.setEmployeeAllocationList(employeeAllocations);
		logger.info("MapAssociatesServiceImpl::updateMappedAssociates::mapAssociatesModel::{}", mapAssociatesModel);
		return mapAssociatesModel;
	}
	
	private void createEmpAllEntity(MapAssociatesModel mapAssociatesModel, List<EmpForm> list,
			List<EmployeeAllocation> employeeAllocations) {
		logger.info("MapAssociatesServiceImpl::createEmpAllEntity::start");
		if(null != list && !list.isEmpty()) {
			final EmployeeAllocation allocation = new EmployeeAllocation();
			allocation.setAccountId(mapAssociatesModel.getAccountId());
			allocation.setProjectId(mapAssociatesModel.getProjectId());
			list.forEach(lead -> {
				if(null != lead.getEmpId() && lead.getEmpId() > 0) {
					EmployeeModel employee = employeeService.findById(lead.getEmpId()).get();
					Employee emp = employeeService.convertToEntity(employee);
					allocation.setEmployee(emp);
					allocation.setRoleId(lead.getRoleId());
					allocation.setVerticalId(1L);
					allocation.setAllocationType(CCOAppliationConstants.FULL_ALLOCATION);
					allocation.setStatus(CCOAppliationConstants.EMP_STATUS_ACTIVE);
					allocation.setStartDate(Calendar.getInstance().getTime());
					employeeAllocations.add(allocation);	
				}
			});
		}
		logger.info("MapAssociatesServiceImpl::createEmpAllEntity::end");
	}

}
