package com.vam.cco.services.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.cco.model.ExportModel;
import com.vam.cco.services.EmployeeAllocationService;
import com.vam.cco.services.FileExportService;
import com.vam.cco.services.ProjectRoleService;
import com.vam.cco.util.CCOAppliationConstants;
import com.vam.cco.util.FileExportUtil;

@Service
public class FileExportServiceImpl implements FileExportService {
	
	private final Logger logger = LoggerFactory.getLogger(FileExportServiceImpl.class);

	
	private FileExportUtil fileExportUtil;
	
	private EmployeeAllocationService employeeAllocationService;
	
	private ProjectRoleService projectroleService;
	
	public FileExportServiceImpl() {
	}
	
	@Autowired
	public FileExportServiceImpl(FileExportUtil fileExportUtil,
			EmployeeAllocationService employeeAllocationService,
			ProjectRoleService projectroleService) {
		this.fileExportUtil = fileExportUtil;
		this.employeeAllocationService = employeeAllocationService;
		this.projectroleService = projectroleService;
	}
	
	@Override
	public XSSFWorkbook createExportByAccount(Long accountId) {
		logger.info("FileExportServiceImpl::createExportByAccount::start {}", accountId);
		
		List<Long> projectRoleIds = projectroleService.findLeadsAndAssociates(
				Arrays.asList(CCOAppliationConstants.EXECUTIVE_VICE_PRESIDENT, 
						CCOAppliationConstants.SENIOR_VICE_PRESIDENT, 
						CCOAppliationConstants.VICE_PRESIDENT, 
						CCOAppliationConstants.ASSOCIATE_VICE_PRESIDENT,
						CCOAppliationConstants.SENIOR_DIRECTOR, 
						CCOAppliationConstants.DIRECTOR, 
						CCOAppliationConstants.ASSOCIATE_DIRECTOR,
						CCOAppliationConstants.SENIOR_MANAGER));
		
		List<ExportModel> exportModels = employeeAllocationService.exportByAccountId(accountId, projectRoleIds);
		logger.info("Export models fetched for account {}: {}", accountId, exportModels);
		XSSFWorkbook workbook = fileExportUtil.createExport(exportModels);
		logger.info("Workbook created for account {}: {}", accountId, workbook);
		return workbook;
	}

	@Override
	public XSSFWorkbook createExportByAccountAndProject(Long accountId, List<Long> projectId) {
		logger.info("FileExportServiceImpl::createExportByAccountAndProject::start accountId: {}, projectIds: {}", accountId, projectId);
		List<ExportModel> exportModels = employeeAllocationService.exportByAccountAndProjectId(accountId, projectId);
		logger.info("Export models fetched for account {} and projects {}: {}", accountId, projectId, exportModels);
		XSSFWorkbook workbook = fileExportUtil.createExport(exportModels);
		logger.info("Workbook created for account {} and projects {}: {}", accountId, projectId, workbook);
		return workbook;
	}

}
