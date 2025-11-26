package com.vam.cco.model;

import java.util.List;

import com.vam.cco.dao.entity.EmployeeAllocation;

public class MapAssociatesModel {

	private List<EmpForm> leaderShipList;
	
	private List<EmpForm> directorList;
	
	private List<EmpForm> managerList;
	
	private List<EmployeeAllocation> employeeAllocationList;
	
	private Long accountId;
	private Long projectId;
	private Long empAllId;
	private Long empId;
	private Long roleId;

	public List<EmpForm> getLeaderShipList() {
		return leaderShipList;
	}

	public void setLeaderShipList(List<EmpForm> leaderShipList) {
		this.leaderShipList = leaderShipList;
	}

	public List<EmpForm> getDirectorList() {
		return directorList;
	}

	public void setDirectorList(List<EmpForm> directorList) {
		this.directorList = directorList;
	}

	public List<EmpForm> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<EmpForm> managerList) {
		this.managerList = managerList;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public List<EmployeeAllocation> getEmployeeAllocationList() {
		return employeeAllocationList;
	}

	public void setEmployeeAllocationList(List<EmployeeAllocation> employeeAllocationList) {
		this.employeeAllocationList = employeeAllocationList;
	}

	public Long getEmpAllId() {
		return empAllId;
	}

	public void setEmpAllId(Long empAllId) {
		this.empAllId = empAllId;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "MapAssociatesModel [leaderShipList=" + leaderShipList + ", directorList=" + directorList
				+ ", managerList=" + managerList + ", employeeAllocationList=" + employeeAllocationList + ", accountId="
				+ accountId + ", projectId=" + projectId + ", empAllId=" + empAllId + ", empId=" + empId + ", roleId="
				+ roleId + "]";
	}
	
}
