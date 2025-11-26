package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum PerformanceStatusEnum {
	High("High"),
	Medium("Meidum"),
	Low("Low");
	
	private String performanceStatus;

	private PerformanceStatusEnum(String performanceStatus) {
		this.performanceStatus = performanceStatus;
	}
	
	public String getPerformanceStatus() {
		return performanceStatus;
	}
	
	public static PerformanceStatusEnum fromString(String text) {
		for (PerformanceStatusEnum b : PerformanceStatusEnum.values()) {
			if (b.performanceStatus.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
	
	public static List<String> getAllStatus() {
		List<String> statusList = new ArrayList<>();
		for (PerformanceStatusEnum b : PerformanceStatusEnum.values()) {
			statusList.add(b.performanceStatus);
		}
		return statusList;
	}
}
