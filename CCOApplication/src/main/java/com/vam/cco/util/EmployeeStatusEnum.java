package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum EmployeeStatusEnum {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    ONBOARDING("Onboarding"),
    OFFBOARDED("Offboarded"),
    TERMINATED("Terminated");

    private final String status;

    EmployeeStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    
    public static List<String> getAllStatus() {
		List<String> statusList = new ArrayList<>();
		for (EmployeeStatusEnum b : EmployeeStatusEnum.values()) {
			statusList.add(b.status);
		}
		return statusList;
	}
}
