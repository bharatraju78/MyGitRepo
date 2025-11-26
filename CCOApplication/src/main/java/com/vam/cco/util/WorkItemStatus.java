package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum WorkItemStatus {
	CREATED,
    IN_PROGRESS,
    COMPLETED;
    
    public static List<String> getAllStatus() {
		List<String> statusList = new ArrayList<>();
		for (WorkItemStatus b : WorkItemStatus.values()) {
			statusList.add(b.name());
		}
		return statusList;
	}
}