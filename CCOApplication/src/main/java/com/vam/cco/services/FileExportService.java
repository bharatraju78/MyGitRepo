package com.vam.cco.services;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface FileExportService {

	public XSSFWorkbook createExportByAccount(Long accountId);
	
	public XSSFWorkbook createExportByAccountAndProject(Long accountId, List<Long> projectId);
}
