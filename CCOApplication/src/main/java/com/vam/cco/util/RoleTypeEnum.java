package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum RoleTypeEnum {
	
	BLANK("---Select---"),
	PM("Project Management"),
	DEV("Business Analysis"),
	BA("Development"),
	QA("Quality Assurance");
	
	private String value;
	
	RoleTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static RoleTypeEnum fromString(String text) {
		for (RoleTypeEnum roleTypeEnum : RoleTypeEnum.values()) {
			if (roleTypeEnum.value.equalsIgnoreCase(text)) {
				return roleTypeEnum;
			}
		}
		return null;
	}
	
	public static List<String> getAllRoleTypes() {
		List<String> roleTypeList = new ArrayList<>();
		for (RoleTypeEnum roleTypeEnum : RoleTypeEnum.values()) {
			roleTypeList.add(roleTypeEnum.value);
		}
		return roleTypeList;
	}

}
