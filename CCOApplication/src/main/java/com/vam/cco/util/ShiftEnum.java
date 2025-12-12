package com.vam.cco.util;

import java.util.ArrayList;
import java.util.List;

public enum ShiftEnum {
	
	BLANK("---Select---"),
	SHIFT_A("Shift A"),
	SHIFT_B("Shift B"),
	SHIFT_C("Shift C"),
	SHIFT_D("Shift D");
	
	private String value;
	
	ShiftEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static ShiftEnum fromString(String text) {
		for (ShiftEnum shiftEnum : ShiftEnum.values()) {
			if (shiftEnum.value.equalsIgnoreCase(text)) {
				return shiftEnum;
			}
		}
		return null;
	}
	
	public static List<String> getAllShifts() {
		List<String> shiftList = new ArrayList<>();
		for (ShiftEnum shiftEnum : ShiftEnum.values()) {
			shiftList.add(shiftEnum.value);
		}
		return shiftList;
	}

}
