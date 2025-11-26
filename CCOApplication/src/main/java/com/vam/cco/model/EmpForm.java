package com.vam.cco.model;

public class EmpForm {

	private Long empAllId;
	private Long empId;
	private String empName;
	private Long roleId;
	private String roleName;
	
	public EmpForm() {
	}
	
	public EmpForm(Long empAllId, Long empId, String empName, Long roleId, String roleName) {
		this.empAllId = empAllId;
		this.empId = empId;
		this.empName = empName;
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "EmpForm [empId=" + empId + ", empName=" + empName + ", roleId=" + roleId + ", roleName=" + roleName
				+ "]";
	}

	public Long getEmpAllId() {
		return empAllId;
	}

	public void setEmpAllId(Long empAllId) {
		this.empAllId = empAllId;
	}
	
	
}
