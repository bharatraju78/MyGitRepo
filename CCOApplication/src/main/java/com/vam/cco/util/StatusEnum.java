package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum StatusEnum {

	ACTIVE("Active"),
	INACTIVE("Inactive"),
	CLOSED("Closed"),
	SUSPENDED("Suspended"),
	TERMINATED("Terminated");

	private String status;

	StatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static StatusEnum fromString(String text) {
		for (StatusEnum b : StatusEnum.values()) {
			if (b.status.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
	
	public static List<String> getAllStatus() {
		List<String> statusList = new ArrayList<>();
		for (StatusEnum b : StatusEnum.values()) {
			statusList.add(b.status);
		}
		return statusList;
	}
}
