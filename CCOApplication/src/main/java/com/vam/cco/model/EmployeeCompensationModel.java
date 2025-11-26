package com.vam.cco.model;

import java.util.List;

import com.vam.cco.dao.entity.EmployeeCompensation;

public class EmployeeCompensationModel {

	private List<EmployeeCompensation> employeeCompensations;

	public List<EmployeeCompensation> getEmployeeCompensations() {
		return employeeCompensations;
	}

	public void setEmployeeCompensations(List<EmployeeCompensation> employeeCompensations) {
		this.employeeCompensations = employeeCompensations;
	}

	@Override
	public String toString() {
		return "EmployeeCompensationModel [employeeCompensations=" + employeeCompensations + "]";
	}
	
	
}
