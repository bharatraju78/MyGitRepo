package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum ProjectRoleEnum {
	
	BLANK("---Select---"),
	ASSOCIATE_TRAINEE("Trainee"),
	ASSOCIATE("Software Engineer"),
	SENIOR_ASSOCIATE("Senior Software Engineer"),
	LEAD("Technical Lead"),
	SENIOR_LEAD("Senior Technical Lead"),
	ARCHITECT("Architect"),
	SENIOR_ARCHITECT("Senior Architect"),
	MANAGER("Project Manager"),
	SERNIOR_MANAGER("Delivery Manager"),
	SOLUTION_ARCHITECT("Solution Architect");
	
	private String value;
	
	ProjectRoleEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static ProjectRoleEnum fromString(String text) {
		for (ProjectRoleEnum projectRoleEnum : ProjectRoleEnum.values()) {
			if (projectRoleEnum.value.equalsIgnoreCase(text)) {
				return projectRoleEnum;
			}
		}
		return null;
	}
	
	public static List<String> getAllProjectRoles() {
		List<String> projectRoleList = new ArrayList<>();
		for (ProjectRoleEnum projectRoleEnum : ProjectRoleEnum.values()) {
			projectRoleList.add(projectRoleEnum.value);
		}
		return projectRoleList;
	}

}
