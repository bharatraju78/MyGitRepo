package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.vam.cco.dao.entity.ProjectRole;

public class ExportModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1269362802894766544L;
	
	private String vamId;
	private String name;
	private Date doj;
	private String gradeName;
	private String designationName;
	private String roleName;
	private String verticalName;
	private String accName;
	private String projectName;
	private String skillDataFromld;
	private String currentSkill;
	private String status;
	private String statusWithDays;
	private String vamExp;
	private String totalExp;
	private String finalStatus;
	private Date resignationDate;
	private String performanceAsPerpm;
	private String attritionRisk;
	private String ror;
	private Date startDate;
	private Date endDate;
	private Date rorDate;
	private String rorComments;
	private Date rorOnBoadingDate;
	private Double ctc;
	private String todo;
	private String yearOneHike;
	private String yearTwoHike;
	private Long allocationPercentage;
	private String allocationType;
	private Long projectRoleId;
	private Long employeeId;
	private Long accountId;
	private Long empAllId;
	private Long verticalId;
	private Long projectId;
	private String replName;
	private String replVAMId;
	private Date ctcStartDate;
	private Date ctcEndDate;

	private List<ProjectRole> projectRoles;
	
	public ExportModel(String vamId, String name, Date doj, String gradeName, String designationName, String roleName,
			String verticalName, String accName, String projectName, String skillDataFromld, String currentSkill,
			String status, String statusWithDays, String vamExp, String totalExp, String finalStatus,
			Date resignationDate, String performanceAsPerpm, String attritionRisk, String ror, Date startDate,
			Date endDate, Date rorDate, String rorComments, Date rorOnBoadingDate, Double ctc, String todo,
			String yearOneHike, String yearTwoHike, Long allocationPercentage, String allocationType, Long projectRoleId,
			Long employeeId, Long accountId, Long empAllId, Long verticalId, Long projectId, String replName,
			String replVAMId, Date ctcStartDate, Date ctcEndDate) {
		super();
		this.vamId = vamId;
		this.name = name;
		this.doj = doj;
		this.gradeName = gradeName;
		this.designationName = designationName;
		this.roleName = roleName;
		this.verticalName = verticalName;
		this.accName = accName;
		this.projectName = projectName;
		this.skillDataFromld = skillDataFromld;
		this.currentSkill = currentSkill;
		this.status = status;
		this.statusWithDays = statusWithDays;
		this.vamExp = vamExp;
		this.totalExp = totalExp;
		this.finalStatus = finalStatus;
		this.resignationDate = resignationDate;
		this.performanceAsPerpm = performanceAsPerpm;
		this.attritionRisk = attritionRisk;
		this.ror = ror;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rorDate = rorDate;
		this.rorComments = rorComments;
		this.rorOnBoadingDate = rorOnBoadingDate;
		this.ctc = ctc;
		this.todo = todo;
		this.yearOneHike = yearOneHike;
		this.yearTwoHike = yearTwoHike;
		this.allocationPercentage = allocationPercentage;
		this.allocationType = allocationType;
		this.projectRoleId = projectRoleId;
		this.employeeId = employeeId;
		this.accountId = accountId;
		this.empAllId = empAllId;
		this.verticalId = verticalId;
		this.projectId = projectId;
		this.replName = replName;
		this.replVAMId = replVAMId;
		this.ctcStartDate = ctcStartDate;
		this.ctcEndDate = ctcEndDate;
	}
	public String getVamId() {
		return vamId;
	}
	public void setVamId(String vamId) {
		this.vamId = vamId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getVerticalName() {
		return verticalName;
	}
	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSkillDataFromld() {
		return skillDataFromld;
	}
	public void setSkillDataFromld(String skillDataFromld) {
		this.skillDataFromld = skillDataFromld;
	}
	public String getCurrentSkill() {
		return currentSkill;
	}
	public void setCurrentSkill(String currentSkill) {
		this.currentSkill = currentSkill;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusWithDays() {
		return statusWithDays;
	}
	public void setStatusWithDays(String statusWithDays) {
		this.statusWithDays = statusWithDays;
	}
	public String getVamExp() {
		return vamExp;
	}
	public void setVamExp(String vamExp) {
		this.vamExp = vamExp;
	}
	public String getTotalExp() {
		return totalExp;
	}
	public void setTotalExp(String totalExp) {
		this.totalExp = totalExp;
	}
	public String getFinalStatus() {
		return finalStatus;
	}
	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}
	public Date getResignationDate() {
		return resignationDate;
	}
	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}
	public String getPerformanceAsPerpm() {
		return performanceAsPerpm;
	}
	public void setPerformanceAsPerpm(String performanceAsPerpm) {
		this.performanceAsPerpm = performanceAsPerpm;
	}
	public String getAttritionRisk() {
		return attritionRisk;
	}
	public void setAttritionRisk(String attritionRisk) {
		this.attritionRisk = attritionRisk;
	}
	public String getRor() {
		return ror;
	}
	public void setRor(String ror) {
		this.ror = ror;
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
	public Double getCtc() {
		if(null == ctc) {
			ctc = 0.0;
		}
		return ctc;
	}
	public void setCtc(Double ctc) {
		this.ctc = ctc;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public String getYearOneHike() {
		return yearOneHike;
	}
	public void setYearOneHike(String yearOneHike) {
		this.yearOneHike = yearOneHike;
	}
	public String getYearTwoHike() {
		return yearTwoHike;
	}
	public void setYearTwoHike(String yearTwoHike) {
		this.yearTwoHike = yearTwoHike;
	}
	public Long getAllocationPercentage() {
		return allocationPercentage;
	}
	public void setAllocationPercentage(Long allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}
	public String getAllocationType() {
		return allocationType;
	}
	public void setAllocationType(String allocationType) {
		this.allocationType = allocationType;
	}
	
	public List<ProjectRole> getProjectRoles() {
		return projectRoles;
	}
	public void setProjectRoles(List<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}
	public Long getProjectRoleId() {
		return projectRoleId;
	}
	public void setProjectRoleId(Long projectRoleId) {
		this.projectRoleId = projectRoleId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getEmpAllId() {
		return empAllId;
	}
	public void setEmpAllId(Long empAllId) {
		this.empAllId = empAllId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getVerticalId() {
		return verticalId;
	}
	public void setVerticalId(Long verticalId) {
		this.verticalId = verticalId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
	public Date getCtcStartDate() {
		return ctcStartDate;
	}
	public void setCtcStartDate(Date ctcStartDate) {
		this.ctcStartDate = ctcStartDate;
	}
	public Date getCtcEndDate() {
		return ctcEndDate;
	}
	public void setCtcEndDate(Date ctcEndDate) {
		this.ctcEndDate = ctcEndDate;
	}
	@Override
	public String toString() {
		return "ExportModel [vamId=" + vamId + ", name=" + name + ", doj=" + doj + ", gradeName=" + gradeName
				+ ", designationName=" + designationName + ", roleName=" + roleName + ", verticalName=" + verticalName
				+ ", accName=" + accName + ", projectName=" + projectName + ", skillDataFromld=" + skillDataFromld
				+ ", currentSkill=" + currentSkill + ", status=" + status + ", statusWithDays=" + statusWithDays
				+ ", vamExp=" + vamExp + ", totalExp=" + totalExp + ", finalStatus=" + finalStatus
				+ ", resignationDate=" + resignationDate + ", performanceAsPerpm=" + performanceAsPerpm
				+ ", attritionRisk=" + attritionRisk + ", ror=" + ror + ", startDate=" + startDate + ", endDate="
				+ endDate + ", rorDate=" + rorDate + ", rorComments=" + rorComments + ", rorOnBoadingDate="
				+ rorOnBoadingDate + ", ctc=" + ctc + ", todo=" + todo + ", yearOneHike=" + yearOneHike
				+ ", yearTwoHike=" + yearTwoHike + ", allocationPercentage=" + allocationPercentage
				+ ", allocationType=" + allocationType + ", projectRoleId=" + projectRoleId + ", employeeId="
				+ employeeId + ", accountId=" + accountId + ", empAllId=" + empAllId + ", verticalId=" + verticalId
				+ ", projectId=" + projectId + ", replName=" + replName + ", replVAMId=" + replVAMId + ", ctcStartDate="
				+ ctcStartDate + ", ctcEndDate=" + ctcEndDate + ", projectRoles=" + projectRoles + "]";
	}
	
	
}
