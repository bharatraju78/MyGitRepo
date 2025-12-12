package com.vam.cco.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.vam.cco.model.ExportModel;

@Component
@PropertySource("classpath:xls_formulas.properties")
public class FileExportUtil {
	
	@Autowired
	private Environment env;
	
	private Logger logger = LoggerFactory.getLogger(FileExportUtil.class);
	
	public XSSFWorkbook createExport(List<ExportModel> exportModels) {
		logger.info("FileExportUtil::createExport::start with {} export models", exportModels.size());
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(CCOAppliationConstants.RESOURCE_MASTER);
		sheet = createHeaderRow(sheet, workbook);
		sheet = createDataRow(sheet, exportModels);
		logger.info("FileExportUtil::createExport::end");
		return workbook;
	}

	private XSSFSheet createHeaderRow(XSSFSheet sheet, XSSFWorkbook workbook) {
		logger.info("FileExportUtil::createHeaderRow::start");
		int columnIndex = 0;
		
		XSSFCellStyle greenCellStyle = getHeadersStyle(workbook);
		
        XSSFRow headerRow = sheet.createRow(0);
        
        for (String header : CCOAppliationConstants.headerList) {
			XSSFCell cell = headerRow.createCell(columnIndex);
			cell.setCellValue(header);
			cell.setCellStyle(greenCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
		}
        
        createDynamicHeaders(headerRow, columnIndex);
		
		logger.info("FileExportUtil::createHeaderRow::end");
		return sheet;
	}

	
	
	private XSSFSheet createDataRow(XSSFSheet sheet, List<ExportModel> exportModels) {
		logger.info("FileExportUtil::createDataRow::start");
		int columnIndex = 0;
		int rowIndex = 1;
		XSSFCellStyle dataCellStyle = getDataStyle(sheet.getWorkbook());
		XSSFCellStyle dynamicCellStyle = getDynamicFieldsStyle(sheet.getWorkbook());
		XSSFCellStyle dateCellStyle  = createDateFormat(sheet.getWorkbook());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mmm-yyyy");
		for (ExportModel exportModel : exportModels) {
			logger.info("FileExportUtil::createDataRow::rowIndex = {}", rowIndex);
			XSSFRow dataRow = sheet.createRow(rowIndex);

			XSSFCell cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getVamId());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getName());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			if(null != exportModel.getDoj()) {
				cell.setCellValue(dateFormat.format(exportModel.getDoj()));
				//cell.setCellStyle(dateCellStyle);
			}
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getGradeName());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getDesignationName());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getRoleName());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getVerticalName());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getAccName());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getProjectName());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getSkillDataFromld());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getCurrentSkill());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getStatus());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getStatusWithDays());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getVamExp());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getTotalExp());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getFinalStatus());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			if(null != exportModel.getResignationDate()) {
				cell.setCellValue(exportModel.getResignationDate());
				cell.setCellStyle(dateCellStyle);
			}
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getPerformanceAsPerpm());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getAttritionRisk());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getRor());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			if(null != exportModel.getCtcStartDate()) {
				cell.setCellValue(exportModel.getCtcStartDate());
				cell.setCellStyle(dateCellStyle);
			} else {
				cell.setCellValue("");
				cell.setCellStyle(dataCellStyle);
			}
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			if(null != exportModel.getCtcEndDate()) {
				cell.setCellValue(exportModel.getCtcEndDate());
				cell.setCellStyle(dateCellStyle);
			} else {
				cell.setCellValue("");
				cell.setCellStyle(dataCellStyle);
			}
			//cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			if(null != exportModel.getRorDate()) {
				cell.setCellValue(dateFormat.format(exportModel.getRorDate()));
			}
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getRorComments());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			if(null != exportModel.getRorOnBoadingDate()) {	
				cell.setCellValue(dateFormat.format(exportModel.getRorOnBoadingDate()));
			}
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getCtc().doubleValue());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getTodo());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getYearOneHike());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue(exportModel.getYearTwoHike());
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			
			cell = dataRow.createCell(columnIndex);
			cell.setCellValue("");
			cell.setCellStyle(dataCellStyle);
			sheet.autoSizeColumn(columnIndex);
			columnIndex++;
			
			
			setDynamicFields(dataRow, columnIndex, rowIndex+1, dynamicCellStyle);
			
			rowIndex++;
			columnIndex = 0;
		}
		return sheet;
	}
	
	private void setDynamicFields(XSSFRow dataRow, int columnIndex, int rowIndex, XSSFCellStyle dynamicCellStyle) {
		//logger.info("FileExportUtil::setDynamicFields::start");
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.january.0");
		String formula = env.getProperty("xls.formula.january.0");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.february.1");
		formula = env.getProperty("xls.formula.february.1");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.march.2");
		formula = env.getProperty("xls.formula.march.2");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.april.3");
		formula = env.getProperty("xls.formula.april.3");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.May.4");
		formula = env.getProperty("xls.formula.May.4");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.june.5");
		formula = env.getProperty("xls.formula.june.5");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.july.6");
		formula = env.getProperty("xls.formula.july.6");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.august.7");
		formula = env.getProperty("xls.formula.august.7");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.september.8");
		formula = env.getProperty("xls.formula.september.8");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.october.9");
		formula = env.getProperty("xls.formula.october.9");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.novmber.10");
		formula = env.getProperty("xls.formula.novmber.10");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.december.11");
		formula = env.getProperty("xls.formula.december.11");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.january.12");
		formula = env.getProperty("xls.formula.january.12");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.february.13");
		formula = env.getProperty("xls.formula.february.13");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.march.14");
		formula = env.getProperty("xls.formula.march.14");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.april.15");
		formula = env.getProperty("xls.formula.april.15");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.may.16");
		formula = env.getProperty("xls.formula.May.16");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.june.17");
		formula = env.getProperty("xls.formula.june.17");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.july.18");
		formula = env.getProperty("xls.formula.july.18");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.august.19");
		formula = env.getProperty("xls.formula.august.19");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.september.20");
		formula = env.getProperty("xls.formula.september.20");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.october.21");
		formula = env.getProperty("xls.formula.october.21");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.november.22");
		formula = env.getProperty("xls.formula.november.22");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.december.23");
		formula = env.getProperty("xls.formula.december.23");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.jan.0");
		formula = env.getProperty("xls.formula.jan.0");
		columnIndex =  setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.feb.1");
		formula = env.getProperty("xls.formula.feb.1");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.mar.2");
		formula = env.getProperty("xls.formula.mar.2");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.apr.3");
		formula = env.getProperty("xls.formula.apr.3");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.may.4");
		formula = env.getProperty("xls.formula.may.4");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.jun.5");
		formula = env.getProperty("xls.formula.jun.5");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.jul.6");
		formula = env.getProperty("xls.formula.jul.6");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.aug.7");
		formula = env.getProperty("xls.formula.aug.7");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.sep.8");
		formula = env.getProperty("xls.formula.sep.8");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.oct.9");
		formula = env.getProperty("xls.formula.oct.9");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.nov.10");
		formula = env.getProperty("xls.formula.nov.10");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.dec.11");
		formula = env.getProperty("xls.formula.dec.11");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.jan.12");
		formula = env.getProperty("xls.formula.jan.12");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.feb.13");
		formula = env.getProperty("xls.formula.feb.13");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.mar.14");
		formula = env.getProperty("xls.formula.mar.14");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.apr.15");
		formula = env.getProperty("xls.formula.apr.15");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.may.16");
		formula = env.getProperty("xls.formula.may.16");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.jun.17");
		formula = env.getProperty("xls.formula.jun.17");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.jul.18");
		formula = env.getProperty("xls.formula.jul.18");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.aug.19");
		formula = env.getProperty("xls.formula.aug.19");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.sep.20");
		formula = env.getProperty("xls.formula.sep.20");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.oct.21");
		formula = env.getProperty("xls.formula.oct.21");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.nov.22");
		formula = env.getProperty("xls.formula.nov.22");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		//logger.info("FileExportUtil::setDynamicFields::key = {}", "xls.formula.dec.23");
		formula = env.getProperty("xls.formula.dec.23");
		columnIndex = setDynamicCell(dataRow, columnIndex, rowIndex, dynamicCellStyle, formula);
		
		XSSFCell cell = dataRow.createCell(columnIndex);
		cell.setCellValue("");
		cell.setCellStyle(dynamicCellStyle);
		columnIndex++;
		
		//logger.info("FileExportUtil::setDynamicFields::end");
	}

	private int setDynamicCell(XSSFRow dataRow, int columnIndex, int rowIndex, XSSFCellStyle dynamicCellStyle, String formula) {
		//logger.info("FileExportUtil::setDynamicCell::start with columnIndex = {}", columnIndex);
		formula = formula.replaceAll("rowIndex", String.valueOf(rowIndex));
//		logger.info("FileExportUtil::setDynamicFields::formula = {}", formula);
		XSSFCell cell = dataRow.createCell(columnIndex);
		cell.setCellFormula(formula);
		cell.setCellStyle(dynamicCellStyle);
		columnIndex++;
		//logger.info("FileExportUtil::setDynamicCell::end with columnIndex = {}", columnIndex);
		return columnIndex;
	}
	private XSSFCellStyle getDataStyle(XSSFWorkbook workbook) {
		logger.info("FileExportUtil::getHeadersStyle::start");
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Calibri");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(false);
		font.setItalic(false);
		
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
		cellStyle.setBorderTop(BorderStyle.MEDIUM);
		cellStyle.setBorderRight(BorderStyle.MEDIUM);
		cellStyle.setBorderLeft(BorderStyle.MEDIUM);
		cellStyle.setFont(font);
		logger.info("FileExportUtil::getHeadersStyle::end");
		return cellStyle;
	}
	
	private XSSFCellStyle createDateFormat(XSSFWorkbook workbook) {
		logger.info("FileExportUtil::createDataFormat::start");
		XSSFCellStyle dateCellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		short format = createHelper.createDataFormat().getFormat(DateFormatConverter.convert(Locale.ENGLISH, "dd-MMM-yy"));
		dateCellStyle.setDataFormat(format);
		
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Calibri");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(false);
		font.setItalic(false);
		
		dateCellStyle.setBorderBottom(BorderStyle.MEDIUM);
		dateCellStyle.setBorderTop(BorderStyle.MEDIUM);
		dateCellStyle.setBorderRight(BorderStyle.MEDIUM);
		dateCellStyle.setBorderLeft(BorderStyle.MEDIUM);
		dateCellStyle.setFont(font);
		logger.info("FileExportUtil::createDataFormat::end");
		return dateCellStyle;
	}
	private XSSFCellStyle getHeadersStyle(XSSFWorkbook workbook) {
		logger.info("FileExportUtil::getHeadersStyle::start");
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Calibri");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		font.setItalic(false);
		
		XSSFCellStyle greenCellStyle = workbook.createCellStyle();
		java.awt.Color green = new java.awt.Color(218, 242, 208);
		greenCellStyle.setFillForegroundColor(new XSSFColor(green, new DefaultIndexedColorMap()));
		greenCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
		greenCellStyle.setBorderBottom(BorderStyle.MEDIUM);
		greenCellStyle.setBorderTop(BorderStyle.MEDIUM);
		greenCellStyle.setBorderRight(BorderStyle.MEDIUM);
		greenCellStyle.setBorderLeft(BorderStyle.MEDIUM);
		greenCellStyle.setFont(font);
		logger.info("FileExportUtil::getHeadersStyle::end");
		return greenCellStyle;
	}
	
	private XSSFCellStyle getDynamicHeadersStyle(XSSFWorkbook workbook) {
		logger.info("FileExportUtil::getHeadersStyle::start");
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Calibri");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		font.setItalic(false);
		
		CreationHelper createHelper = workbook.getCreationHelper();
		XSSFCellStyle greenCellStyle = workbook.createCellStyle();
		short format = createHelper.createDataFormat().getFormat(DateFormatConverter.convert(Locale.ENGLISH, "dd-MMM-yy"));
		greenCellStyle.setDataFormat(format);
		
		java.awt.Color green = new java.awt.Color(218, 242, 208);
		greenCellStyle.setFillForegroundColor(new XSSFColor(green, new DefaultIndexedColorMap()));
		greenCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
		greenCellStyle.setBorderBottom(BorderStyle.MEDIUM);
		greenCellStyle.setBorderTop(BorderStyle.MEDIUM);
		greenCellStyle.setBorderRight(BorderStyle.MEDIUM);
		greenCellStyle.setBorderLeft(BorderStyle.MEDIUM);
		greenCellStyle.setFont(font);
		logger.info("FileExportUtil::getHeadersStyle::end");
		return greenCellStyle;
	}
	
	private XSSFCellStyle getDynamicFieldsStyle(XSSFWorkbook workbook) {
		logger.info("FileExportUtil::getHeadersStyle::start");
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Calibri");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(false);
		font.setItalic(false);
		
		XSSFCellStyle greenCellStyle = workbook.createCellStyle();
		java.awt.Color green = new java.awt.Color(225, 225, 0);
		greenCellStyle.setFillForegroundColor(new XSSFColor(green, new DefaultIndexedColorMap()));
		greenCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
		greenCellStyle.setBorderBottom(BorderStyle.MEDIUM);
		greenCellStyle.setBorderTop(BorderStyle.MEDIUM);
		greenCellStyle.setBorderRight(BorderStyle.MEDIUM);
		greenCellStyle.setBorderLeft(BorderStyle.MEDIUM);
		greenCellStyle.setFont(font);
		logger.info("FileExportUtil::getHeadersStyle::end");
		return greenCellStyle;
	}
	
	public void createDynamicHeaders(XSSFRow headerRow, int columnIndex) {
		logger.info("FileExportUtil::createDynamicHeaders::start");
		XSSFCellStyle headerCellStyle = getDynamicHeadersStyle(headerRow.getSheet().getWorkbook());
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		for(String month : CCOAppliationConstants.monthsHeaderList) {
			XSSFCell cell = headerRow.createCell(columnIndex);
			cell.setCellValue(month + " " + year);
			cell.setCellStyle(headerCellStyle);
			columnIndex++;
		}
		year++;
		for(String month : CCOAppliationConstants.monthsHeaderList) {
			XSSFCell cell = headerRow.createCell(columnIndex);
			cell.setCellValue(month + " " + year);
			cell.setCellStyle(headerCellStyle);
			columnIndex++;
		}
		Integer shortYear = Integer.valueOf(new SimpleDateFormat("yy").format(Calendar.getInstance().getTime()));
		for(String month : CCOAppliationConstants.mtsHeaderList) {
			XSSFCell cell = headerRow.createCell(columnIndex);
			cell.setCellValue(month + shortYear);
			cell.setCellStyle(headerCellStyle);
			columnIndex++;
		}
		shortYear++;
		for(String month : CCOAppliationConstants.mtsHeaderList) {
			XSSFCell cell = headerRow.createCell(columnIndex);
			cell.setCellValue(month + shortYear);
			cell.setCellStyle(headerCellStyle);
			columnIndex++;
		}
		
		XSSFCell cell = headerRow.createCell(columnIndex);
		cell.setCellValue("__PowerAppsId__");
		cell.setCellStyle(headerCellStyle);
		
		logger.info("FileExportUtil::createDynamicHeaders::end");
	}
}
