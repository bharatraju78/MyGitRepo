package com.vam.cco.util;

import java.util.Arrays;
import java.util.List;

public class CCOAppliationConstants {

	private CCOAppliationConstants() {}
	
	public static String  ACCOUNT_XLS_READ_COUNT = "com.vam.cco.util.readAccountXlsFile.count";
	
	public static String  PROJECT_XLS_READ_COUNT = "com.vam.cco.util.readProjectXLSFile.count";
	
	public static List<String> headerList = List.of(
			"VAMID", "Name", "DOJ", "Grade", "Designation", "Role", "EC", "Account",
			"Project", "Skill data from L&D", "CurrentSkill", "Status", "Status with days",
			"VAM Exp", "Total Exp", "Final Status", "Resignation data",
			"Performance (High, Medium, Low) as per PMS", "Attrition Risk",
			"Release/ Replacement", "Start Date", "End Date",
			"Release/ Replacement Date", "Release/ Replacement Comments",
			"Release Relacement Onboarding Date", "CTC",
			"Same/ L-1/ GT/Lateral/Associate", "Year 1 Hike", "Year 2 Hike", "Time Frame in Months");
	
	public static List<String> monthsHeaderList = List.of("31 January","28 February","31 March","30 April","31 May",
			"30 June","31 July","31 August","30 September","31 October","30 November","31 December");
	
	public static List<String> mtsHeaderList = List.of("Jan-","Feb-","Mar-","Apr-","May-","Jun-","Jul-","Aug-","Sep-","Oct-","Nov-","Dec-");
	
	
	public static String RESOURCE_MASTER = "Resource Master";
	public static String ROLE_ = "ROLE_";
	public static String EMPTY_STRING = "";
	public static String UNDERSCORE = "_";
	public static String SPACE_STRING = " ";
	public static String ADMIN = "ADMIN";
	
	public static String ROLE_HR = "HR";
	
	public static String EXECUTIVE_VICE_PRESIDENT = "Executive Vice President";
	public static String SENIOR_VICE_PRESIDENT = "Senior Vice President";
	public static String VICE_PRESIDENT = "Vice President";
	public static String ASSOCIATE_VICE_PRESIDENT = "Associate Vice President";
	public static String SENIOR_DIRECTOR = "Senior Director";
	public static String DIRECTOR = "Director";
	public static String ASSOCIATE_DIRECTOR = "Associate Director";
	public static String SENIOR_MANAGER = "Senior Manager";
	public static String PROJECT_MANAGER = "Manager";
	
	public static List<String> leaderShipList = Arrays.asList(CCOAppliationConstants.EXECUTIVE_VICE_PRESIDENT,
			CCOAppliationConstants.SENIOR_VICE_PRESIDENT, CCOAppliationConstants.VICE_PRESIDENT,
			CCOAppliationConstants.ASSOCIATE_VICE_PRESIDENT);
	
	public static List<String> directorList = Arrays.asList(CCOAppliationConstants.SENIOR_DIRECTOR, CCOAppliationConstants.DIRECTOR,
			CCOAppliationConstants.ASSOCIATE_DIRECTOR);
	
	public static List<String> managerList = Arrays.asList(CCOAppliationConstants.SENIOR_MANAGER, CCOAppliationConstants.PROJECT_MANAGER);
	
	public static String FULL_ALLOCATION = "Full Allocation";
	
	public static String EMP_STATUS_ACTIVE = "Active";
	
	public static String EMP_STATUS_INACTIVE = "Inactive";
	
	public static String ASSOCIATE_TYPE_LEADERSHIP = "Leadership";
	public static String ASSOCIATE_TYPE_DIRECTOR = "Director";
	public static String ASSOCIATE_TYPE_MANAGER = "Manager";
	public static String EMPLOYEE_DETAILS = "Employee Details";
	public static String DESIGNATION = "Designation";
	public static String GRADE = "Grade";
	public static String COMPENSATION = "Compensation";
	public static String OFF_BOARD = "Off Board";
	
}
