package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum DesignationEnum {

	EXECUTIVE_VICE_PRESIDENT("Executive Vice President"),
	SENIOR_VICE_PRESIDENT("Senior Vice President"),
	VICE_PRESIDENT("Vice President"),
	ASSOCIATE_VICE_PRESIDENT("Associate Vice President"),
	SENIOR_DIRECTOR("Senior Director"),
	DIRECTOR("Director"),
	ASSOCIATE_DIRECTOR("Associate Director"),
	SENIOR_MANAGER("Senior Manager"),
	PROJECT_MANAGER("Project Manager");
	
	private String status;

	DesignationEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static DesignationEnum fromString(String text) {
		for (DesignationEnum b : DesignationEnum.values()) {
			if (b.status.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
	
	public static List<String> getAllStatus() {
		List<String> statusList = new ArrayList<>();
		for (DesignationEnum b : DesignationEnum.values()) {
			statusList.add(b.status);
		}
		return statusList;
	}
}
