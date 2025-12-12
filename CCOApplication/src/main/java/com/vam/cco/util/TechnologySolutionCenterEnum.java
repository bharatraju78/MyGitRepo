package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum TechnologySolutionCenterEnum {
	
	BLANK("---Select---"),
	PLATFORM_APP_AND_INFRA("Platform App and Infra"),
	DATA_ENGINEERING("Data Engineering"),
	CORE_LEVERAGE("Core Leverage"),
	ADVANCED_ANALYTICS("Advanced Analytics"),
	EMERGING_TECHNOLOGIES("Emerging Tech");
	
	private String value;
	
	TechnologySolutionCenterEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static TechnologySolutionCenterEnum fromString(String text) {
		for (TechnologySolutionCenterEnum techCenterEnum : TechnologySolutionCenterEnum.values()) {
			if (techCenterEnum.value.equalsIgnoreCase(text)) {
				return techCenterEnum;
			}
		}
		return null;
	}
	
	public static List<String> getAllTechCenters() {
		List<String> techCenterList = new ArrayList<>();
		for (TechnologySolutionCenterEnum techCenterEnum : TechnologySolutionCenterEnum.values()) {
			techCenterList.add(techCenterEnum.value);
		}
		return techCenterList;
	}

}
