package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum EmployeeTypeEnum {
	
	BLANK("---Select---"),
	FTE("FTE"),
	SUB("Sub"),
	OFFSHORE("Offshore"),
	UK("UK"),
	CANADA("Canada");
	
	private String value;
	
	EmployeeTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static EmployeeTypeEnum fromString(String text) {
		for (EmployeeTypeEnum employeeTypeEnum : EmployeeTypeEnum.values()) {
			if (employeeTypeEnum.value.equalsIgnoreCase(text)) {
				return employeeTypeEnum;
			}
		}
		return null;
	}
	
	public static List<String> getAllEmployeeTypes() {
		List<String> employeeTypeList = new ArrayList<>();
		for (EmployeeTypeEnum employeeTypeEnum : EmployeeTypeEnum.values()) {
			employeeTypeList.add(employeeTypeEnum.value);
		}
		return employeeTypeList;
	}

}
