package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employee_allocation", uniqueConstraints = { @UniqueConstraint(columnNames = { "emp_all_Id",
		"employee_id", "role_id", "account_id", "project_id", "vertical_id", "allocation_type" }) })

public class EmployeeAllocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 771691965935964266L;

	@Id
	@Column(name = "emp_all_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empAllId;

	@Column(name = "role_id", nullable = false)
	private Long roleId;

	@Column(name = "account_id", nullable = false)
	private Long accountId;

	@Column(name = "project_id", nullable = false)
	private Long projectId;

	@Column(name = "vertical_id", nullable = false)
	private Long verticalId;

	@Column(name = "allocation_type", nullable = false)
	private String allocationType;

	private Date startDate;
	private Date endDate;

	private String ror;
	private Date rorDate;
	private String rorComments;
	private Date rorOnBoadingDate;
	private String performanceAsPerPM;
	private Long allocationPercentage;
	private String status; // Default status is Active
	private String replName;
	private String replVAMId;

	public EmployeeAllocation() {
	}

	public EmployeeAllocation(Long empAllId, Long empId, String empName, Long roleId, String roleName) {
		this.empAllId = empAllId;
		this.roleId = roleId;
		this.empId = empId;
		this.empName = empName;
		this.roleName = roleName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Transient
	private String empName;

	@Transient
	private String roleName;

	@Transient
	private String accountName;

	@Transient
	private String projectName;

	@Transient
	private String ec;
	
	@Transient
	private Long empId;
	
	@Transient
	private String associateType;

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

	public Long getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Long verticalId) {
		this.verticalId = verticalId;
	}

	public String getAllocationType() {
		return allocationType;
	}

	public void setAllocationType(String allocationType) {
		this.allocationType = allocationType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRor() {
		return ror;
	}

	public void setRor(String ror) {
		this.ror = ror;
	}

	public Date getRorDate() {
		return rorDate;
	}

	public void setRorDate(Date rorDate) {
		this.rorDate = rorDate;
	}

	public String getRorComments() {
		return rorComments;
	}

	public void setRorComments(String rorComments) {
		this.rorComments = rorComments;
	}

	public Date getRorOnBoadingDate() {
		return rorOnBoadingDate;
	}

	public void setRorOnBoadingDate(Date rorOnBoadingDate) {
		this.rorOnBoadingDate = rorOnBoadingDate;
	}

	public String getPerformanceAsPerPM() {
		return performanceAsPerPM;
	}

	public void setPerformanceAsPerPM(String performanceAsPerPM) {
		this.performanceAsPerPM = performanceAsPerPM;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	public Long getAllocationPercentage() {
		return allocationPercentage;
	}

	public void setAllocationPercentage(Long allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReplName() {
		return replName;
	}

	public void setReplName(String replName) {
		this.replName = replName;
	}

	public String getReplVAMId() {
		return replVAMId;
	}

	public void setReplVAMId(String replVAMId) {
		this.replVAMId = replVAMId;
	}

	public String getAssociateType() {
		return associateType;
	}

	public void setAssociateType(String associateType) {
		this.associateType = associateType;
	}

	@Override
	public String toString() {
		return "EmployeeAllocation [empAllId=" + empAllId + ", roleId=" + roleId + ", accountId=" + accountId
				+ ", projectId=" + projectId + ", verticalId=" + verticalId + ", allocationType=" + allocationType
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", ror=" + ror + ", rorDate=" + rorDate
				+ ", rorComments=" + rorComments + ", rorOnBoadingDate=" + rorOnBoadingDate + ", performanceAsPerPM="
				+ performanceAsPerPM + ", allocationPercentage=" + allocationPercentage + ", status=" + status
				+ ", replName=" + replName + ", replVAMId=" + replVAMId + ", employee=" + employee + ", empName="
				+ empName + ", roleName=" + roleName + ", accountName=" + accountName + ", projectName=" + projectName
				+ ", ec=" + ec + ", empId=" + empId + ", associateType=" + associateType + "]";
	}
	
	

}
