package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.vam.cco.dao.entity.Designation;
import com.vam.cco.dao.entity.Grade;

public class EmployeeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5821082514892934266L;

	 private Long employeeId;
		
		private String vamId;
		private String name;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date doj;
		private String grade;
		private Long gradeId;
		private String designation;
		private Long designationId;
		private String ec;
		private String emailId;
		private String status;
		private String skillDataFromLD;
		private String currentSkill;
		private String statusWithDays ="";
		private String vamExp = "";
		private String totalExp = "";
		private String finalStatus;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date resignationDate;
		private String performanceAsPerPM;
		private String attritionRisk;
		private String ror;
		@Temporal(TemporalType.DATE)
		private Date startDate;
		@Temporal(TemporalType.DATE)
		private Date endDate;
		@Temporal(TemporalType.DATE)
		private Date rorDate;
		private String rorComments;
		@Temporal(TemporalType.DATE)
		private Date rorOnBoadingDate;
		private Double ctc;
		private String todo;
		private String yearOneHike;
		private String yearTwoHike;
		private Long timeFrameInMonths;
		
		private String createdBy;
		private String modifiedBy;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date createdDate;
		@Temporal(TemporalType.TIMESTAMP)
		private Date modifiedDate;
		
		private List<Grade> grades;
		private List<Designation> designations;
		private List<String> statusList;
		private String dojSearch;
		private Long empAllId;
		private String password;
		private String roleName;
		private String actionFrom;
		private String requestType;
		private Long requestId;
		
		public Long getEmpAllId() {
			return empAllId;
		}

		public void setEmpAllId(Long empAllId) {
			this.empAllId = empAllId;
		}

		public EmployeeModel() {}
		
		public EmployeeModel(Long employeeId, String vamId, String name, String gradeName, String designationName,
				String emailId, String status, Double ctc) {
			this.employeeId = employeeId;
			this.vamId = vamId;
			this.name = name;
			this.grade = gradeName;
			this.designation = designationName;
			this.emailId = emailId;
			this.status = status;
			this.ctc = ctc;
		}
		
		public EmployeeModel(String vamId,  String attritionRisk, Double ctc, String currentSkill, String skillDataFromLD, Date doj, String designation,
				String emailId, String finalString, String grade, Date resignationDate, String status, String totalExp, String vamExp) {
			this.vamId = vamId;
			this.attritionRisk = attritionRisk;
			this.ctc = ctc;
			this.currentSkill = currentSkill;
			this.skillDataFromLD = skillDataFromLD;
			this.doj = doj;
			this.designation = designation;
			this.emailId = emailId;
			this.finalStatus = finalString;
			this.grade = grade;
			this.resignationDate = resignationDate;
			this.status = status;
			this.totalExp = totalExp;
			this.vamExp = vamExp;
			
		}
		
		public EmployeeModel(Long employeeId, String vamId, String name,  String attritionRisk, Double ctc, String currentSkill, String skillDataFromLD, Date doj, String designation,
				String emailId, String finalString, String grade, Date resignationDate, String status, String totalExp, String vamExp) {
			this.employeeId = employeeId;
			this.vamId = vamId;
			this.name = name;
			this.attritionRisk = attritionRisk;
			this.ctc = ctc;
			this.currentSkill = currentSkill;
			this.skillDataFromLD = skillDataFromLD;
			this.doj = doj;
			this.designation = designation;
			this.emailId = emailId;
			this.finalStatus = finalString;
			this.grade = grade;
			this.resignationDate = resignationDate;
			this.status = status;
			this.totalExp = totalExp;
			this.vamExp = vamExp;
			
		}
		
		public EmployeeModel(Long employeeId, String vamId, String name, Date doj,
				Date startDate, Date endDate, String status, Long empAllId, String roleName) {
			this.employeeId = employeeId;
			this.vamId = vamId;
			this.name = name;
			this.doj = doj;
			this.startDate = startDate;
			this.endDate = endDate;
			this.status = status;
			this.empAllId = empAllId;
			this.roleName = roleName;
		}
		
		public EmployeeModel(Long employeeId, String vamId, String name, Date doj,
				Date startDate, Date endDate, String status, Long empAllId) {
			this.employeeId = employeeId;
			this.vamId = vamId;
			this.name = name;
			this.doj = doj;
			this.startDate = startDate;
			this.endDate = endDate;
			this.status = status;
			this.empAllId = empAllId;
		}
		
		public EmployeeModel(String name, String password, String roleName) {
			this.name = name;
			this.password = password;
			this.roleName = roleName;
		}
		
		public EmployeeModel(Long employeeId, String name) {
			this.employeeId = employeeId;
			this.name = name;
		}
		
		public Long getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Long employeeId) {
			this.employeeId = employeeId;
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
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public String getEc() {
			return ec;
		}
		public void setEc(String ec) {
			this.ec = ec;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getSkillDataFromLD() {
			return skillDataFromLD;
		}
		public void setSkillDataFromLD(String skillDataFromLD) {
			this.skillDataFromLD = skillDataFromLD;
		}
		public String getCurrentSkill() {
			return currentSkill;
		}
		public void setCurrentSkill(String currentSkill) {
			this.currentSkill = currentSkill;
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
		public String getPerformanceAsPerPM() {
			return performanceAsPerPM;
		}
		public void setPerformanceAsPerPM(String performanceAsPerPM) {
			this.performanceAsPerPM = performanceAsPerPM;
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
		public Long getTimeFrameInMonths() {
			return timeFrameInMonths;
		}
		public void setTimeFrameInMonths(Long timeFrameInMonths) {
			this.timeFrameInMonths = timeFrameInMonths;
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
		
		public Long getGradeId() {
			return gradeId;
		}
		public void setGradeId(Long gradeId) {
			this.gradeId = gradeId;
		}
		public Long getDesignationId() {
			return designationId;
		}
		public void setDesignationId(Long designationId) {
			this.designationId = designationId;
		}
		public List<Grade> getGrades() {
			return grades;
		}
		public void setGrades(List<Grade> grades) {
			this.grades = grades;
		}
		public List<Designation> getDesignations() {
			return designations;
		}
		public void setDesignations(List<Designation> designations) {
			this.designations = designations;
		}
		public List<String> getStatusList() {
			return statusList;
		}
		public void setStatusList(List<String> statusList) {
			this.statusList = statusList;
		}
		@Override
		public String toString() {
			return "EmployeeModel [employeeId=" + employeeId + ", vamId=" + vamId + ", name=" + name + ", doj=" + doj
					+ ", grade=" + grade + ", gradeId=" + gradeId + ", designation=" + designation + ", designationId="
					+ designationId + ", ec=" + ec + ", emailId=" + emailId + ", status=" + status
					+ ", skillDataFromLD=" + skillDataFromLD + ", currentSkill=" + currentSkill + ", statusWithDays="
					+ statusWithDays + ", vamExp=" + vamExp + ", totalExp=" + totalExp + ", finalStatus=" + finalStatus
					+ ", resignationDate=" + resignationDate + ", performanceAsPerPM=" + performanceAsPerPM
					+ ", attritionRisk=" + attritionRisk + ", ror=" + ror + ", startDate=" + startDate + ", endDate="
					+ endDate + ", rorDate=" + rorDate + ", rorComments=" + rorComments + ", rorOnBoadingDate="
					+ rorOnBoadingDate + ", ctc=" + ctc + ", todo=" + todo + ", yearOneHike=" + yearOneHike
					+ ", yearTwoHike=" + yearTwoHike + ", timeFrameInMonths=" + timeFrameInMonths + ", createdBy="
					+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate + ", modifiedDate="
					+ modifiedDate + ", grades=" + grades + ", designations=" + designations + ", statusList=" + statusList + "]";
		}

		public String getDojSearch() {
			return dojSearch;
		}

		public void setDojSearch(String dojSearch) {
			this.dojSearch = dojSearch;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public String getActionFrom() {
			return actionFrom;
		}

		public void setActionFrom(String actionFrom) {
			this.actionFrom = actionFrom;
		}

		public String getRequestType() {
			return requestType;
		}

		public void setRequestType(String requestType) {
			this.requestType = requestType;
		}

		public Long getRequestId() {
			return requestId;
		}

		public void setRequestId(Long requestId) {
			this.requestId = requestId;
		}
		
		
}