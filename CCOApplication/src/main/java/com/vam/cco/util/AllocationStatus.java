package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum AllocationStatus {
	FULL_ALLOCATION("Full Allocation"),
	PARTIAL_ALLOCATION("Partial Allocation"),
	;
	
	private String status;

	AllocationStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static AllocationStatus fromString(String text) {
		for (AllocationStatus b : AllocationStatus.values()) {
			if (b.status.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
	
	public static List<String> getAllStatus() {
		List<String> statusList = new ArrayList<>();
		for (AllocationStatus b : AllocationStatus.values()) {
			statusList.add(b.status);
		}
		return statusList;
	}
}
