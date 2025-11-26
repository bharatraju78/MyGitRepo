package com.vam.cco.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vam.cco.services.FileExportService;

@Controller
@RequestMapping("/admin/file-export")
public class FileExportController {

	private final Logger logger = LoggerFactory.getLogger(FileExportController.class);
	
	private FileExportService fileExportService;
	
	public FileExportController() {
	}
	
	@Autowired
	public FileExportController(FileExportService fileExportService) {
		this.fileExportService = fileExportService;
	}
	
	@GetMapping("/accountExport/{accountId}")
	public void exportAccount(@PathVariable("accountId") Long accountId, HttpServletResponse response) {
		logger.info("DashboardController::exportAccount::start");
		logger.info("DashboardController::exportAccount::accountId = {}", accountId);
		try {
			XSSFWorkbook workbook = fileExportService.createExportByAccount(accountId);
			response.addHeader("Content-Disposition", "attachment; filename=Consolidated Cost Optimization_V1.xlsx");
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			logger.error("Error while exporting account data", e);
		}
		logger.info("DashboardController::exportAccount::end");
	}
	
	@GetMapping(value = "/exportAccount/{accountId}/{projects}")
	public void exportAccount(@PathVariable("accountId") Long accountId,
			@PathVariable("projects") List<Long> projects,Model model, HttpServletResponse response) {
		logger.info("DashboardController::exportAccount::start");
		logger.info("DashboardController::exportAccount::accountId = {}", accountId);
		logger.info("DashboardController::exportAccount::projects = {}", projects);
		try {
			XSSFWorkbook workbook = fileExportService.createExportByAccountAndProject(accountId, projects);
			response.addHeader("Content-Disposition", "attachment; filename=Consolidated Cost Optimization_V1.xlsx");
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			logger.error("Error while exporting account data", e);
		}
		logger.info("DashboardController::exportAccount::end");
	}
}
