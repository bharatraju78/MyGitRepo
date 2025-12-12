package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum LocationEnum {
	
	BLANK("---Select---"),
	ONSITE("Onsite"),
	OFFSHORE("Offshore");
	
	private String value;
	
	LocationEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static LocationEnum fromString(String text) {
		for (LocationEnum locationEnum : LocationEnum.values()) {
			if (locationEnum.value.equalsIgnoreCase(text)) {
				return locationEnum;
			}
		}
		return null;
	}
	
	public static List<String> getAllLocations() {
		List<String> locationList = new ArrayList<>();
		for (LocationEnum locationEnum : LocationEnum.values()) {
			locationList.add(locationEnum.value);
		}
		return locationList;
	}

}
