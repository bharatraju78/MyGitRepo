package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8900067401318569102L;

	@Id
	@Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
	
	private String vamId;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date doj;
	private Long gradeId;
	private Long designationId;
	
	private String emailId;
	private String skillDataFromLD;
	private String currentSkill;
	private String status;
	private String statusWithDays ="";
	private String vamExp = "";
	private String totalExp = "";
	private String finalStatus;
	@Temporal(TemporalType.DATE)
	private Date resignationDate;
	private String attritionRisk;
	
	private BigDecimal ctc;
	private String todo;
	private String yearOneHike;
	private String yearTwoHike;
	private Long timeFrameInMonths;
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	@Column(name = "modified_by", nullable = false)
	private String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false)
	private Date modifiedDate;
	
	@Transient
	private String designationName;
	
	@Transient
	private String gradeName;
	
	@OneToMany(mappedBy="employee",fetch = FetchType.LAZY,
			cascade = javax.persistence.CascadeType.ALL )
	private Set<EmployeeAllocation> employeeAllocations = new HashSet<EmployeeAllocation>(0);
	
	private String password;
	
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public String getAttritionRisk() {
		return attritionRisk;
	}
	public void setAttritionRisk(String attritionRisk) {
		this.attritionRisk = attritionRisk;
	}
	public BigDecimal getCtc() {
		return ctc;
	}
	public void setCtc(BigDecimal ctc) {
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
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", vamId=" + vamId + ", name=" + name + ", doj=" + doj
				+ ", gradeId=" + gradeId + ", designationId=" + designationId + ", emailId=" + emailId + ", skillDataFromLD="
				+ skillDataFromLD + ", currentSkill=" + currentSkill + ", status=" + status + ", statusWithDays="
				+ statusWithDays + ", vamExp=" + vamExp + ", totalExp=" + totalExp + ", finalStatus=" + finalStatus
				+ ", resignationDate=" + resignationDate + ", attritionRisk=" + attritionRisk + ", ctc=" + ctc
				+ ", todo=" + todo + ", yearOneHike=" + yearOneHike + ", yearTwoHike=" + yearTwoHike
				+ ", timeFrameInMonths=" + timeFrameInMonths + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	public Set<EmployeeAllocation> getEmployeeAllocations() {
		return employeeAllocations;
	}
	public void setEmployeeAllocations(Set<EmployeeAllocation> employeeAllocations) {
		this.employeeAllocations = employeeAllocations;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
