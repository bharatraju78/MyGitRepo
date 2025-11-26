package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeAllocationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1821344496331477043L;
	
	private Long empAllId;
	private Long roleId;
	private Long accountId;
	private Long projectId;
	private String ec;
	private String allocationType;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private String ror;
	private Date rorDate;
	private String rorComments;
	private Date rorOnBoadingDate;
	private String performanceAsPerPM;
	
	private String empName;
	private String roleName;
	private String accountName;
	private String projectName;
	private String replName;
	private String replVAMId;
	
	private String verticalName;
	private Long verticalId;
	private Long empId;
	private String projectRoleId;
	private String projectRoleName;
	private Long allocationPercentage;
	
	private String status;
	private String description;
	
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	
	private String startDateStr;
	private String endDateStr;
	
	
	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public EmployeeAllocationModel() {
	}
	
	public EmployeeAllocationModel(Long empAllId, Long roleId, String roleName, Long accountId, String accName,
			Long projectId, String projectName, String allocationType, Date startDate, Date endDate, 
			Long verticalId, String verticalName, Long empId, String empName, Long allocationPercentage,
			String ror, Date rorDate, String rorComments, Date rorOnBoadingDate, String performanceAsPerPM,
			String replName, String replVAMId) {
		this.empAllId = empAllId;
		this.roleId = roleId;
		this.accountId = accountId;
		this.projectId = projectId;
		this.allocationType = allocationType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.empName = empName;
		this.roleName = roleName;
		this.accountName = accName;
		this.projectName = projectName;
		this.verticalId = verticalId;
		this.verticalName = verticalName;
		this.empId = empId;
		this.allocationPercentage = allocationPercentage;
		this.ror = ror;
		this.rorDate = rorDate;
		this.rorComments = rorComments;
		this.rorOnBoadingDate = rorOnBoadingDate;
		this.performanceAsPerPM = performanceAsPerPM;
		this.replName = replName;
		this.replVAMId = replVAMId;
	}
	
	public EmployeeAllocationModel(String accountName, String projectName, Date startDate, Date endDate, String status,
			String description) {
		this.accountName = accountName;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.description = description;
	}
	
	public EmployeeAllocationModel(String roleName) {
		this.roleName = roleName;
	}
	
	public Long getEmpAllId() {
		return empAllId;
	}
	public void setEmpAllId(Long empAllId) {
		this.empAllId = empAllId;
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
	public String getEc() {
		return ec;
	}
	public void setEc(String ec) {
		this.ec = ec;
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
	
	public String getVerticalName() {
		return verticalName;
	}
	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}
	public Long getVerticalId() {
		return verticalId;
	}
	public void setVerticalId(Long verticalId) {
		this.verticalId = verticalId;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getProjectRoleId() {
		return projectRoleId;
	}
	public void setProjectRoleId(String projectRoleId) {
		this.projectRoleId = projectRoleId;
	}
	public String getProjectRoleName() {
		return projectRoleName;
	}
	public void setProjectRoleName(String projectRoleName) {
		this.projectRoleName = projectRoleName;
	}
	public Long getAllocationPercentage() {
		return allocationPercentage;
	}
	public void setAllocationPercentage(Long allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "EmployeeAllocationModel [empAllId=" + empAllId + ", roleId=" + roleId + ", accountId=" + accountId
				+ ", projectId=" + projectId + ", ec=" + ec + ", allocationType=" + allocationType + ", startDate="
				+ startDate + ", endDate=" + endDate + ", ror=" + ror + ", rorDate=" + rorDate + ", rorComments="
				+ rorComments + ", rorOnBoadingDate=" + rorOnBoadingDate + ", performanceAsPerPM=" + performanceAsPerPM
				+ ", empName=" + empName + ", roleName=" + roleName + ", accountName=" + accountName + ", projectName="
				+ projectName + ", replName=" + replName + ", replVAMId=" + replVAMId + ", verticalName=" + verticalName
				+ ", verticalId=" + verticalId + ", empId=" + empId + ", projectRoleId=" + projectRoleId
				+ ", projectRoleName=" + projectRoleName + ", allocationPercentage=" + allocationPercentage
				+ ", status=" + status + ", description=" + description + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
	
}
