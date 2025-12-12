package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum VerticalsEnum {
	
	BLANK("---Select---"),
	PRODUCT_AND_UNDERWRITING("Product and Underwriting"),
	CLAIMS("Claims"),
	DISTRIBUTION("Distribution"),
	ENTERPRISE_IT("Enterprise IT");
	
	private String value;
	
	VerticalsEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static VerticalsEnum fromString(String text) {
		for (VerticalsEnum verticalsEnum : VerticalsEnum.values()) {
			if (verticalsEnum.value.equalsIgnoreCase(text)) {
				return verticalsEnum;
			}
		}
		return null;
	}
	
	public static List<String> getAllVerticals() {
		List<String> verticalList = new ArrayList<>();
		for (VerticalsEnum verticalsEnum : VerticalsEnum.values()) {
			verticalList.add(verticalsEnum.value);
		}
		return verticalList;
	}

}
