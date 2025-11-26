package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee_designation", uniqueConstraints = @UniqueConstraint(columnNames = { "id", "employee_id",
		"designation_id" }))
public class EmployeeDesignation implements Serializable {

	private static final long serialVersionUID = 8038296064543478560L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "employee_id", nullable = false)
	private Long employeeId;

	@Column(name = "designation_id", nullable = false)
	private Long designationId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "comments")
	private String comments;
	
	@Column(name = "status")
	private String status = "ACTIVE";
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	@Column(name = "modified_by", nullable = false)
	private String modifiedBy;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "modified_date", nullable = false)
	private Date modifiedDate;
	
	@Transient
	private String startDateString;
	@Transient
	private String endDateString;
	
	@Transient
	private String designationName;

	@Transient
	private String actionFrom;
	
	@Transient
	private Boolean hideAddBtn;
	
	@Transient
	private String requestType;
	
	@Transient
	private Long requestId;
	
	public EmployeeDesignation() {
	}
	
	public EmployeeDesignation(String designationName) {
		this.designationName = designationName;
	}
	public EmployeeDesignation(Long employeeId, Long designationId, Date startDate, Date endDate, String comments, 
			String designationName) {
		this.employeeId = employeeId;
		this.designationId = designationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.comments = comments;
		this.designationName = designationName;
	}
	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "EmployeeDesignation [id=" + id + ", employeeId=" + employeeId + ", designationId=" + designationId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", comments=" + comments + "]";
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

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getActionFrom() {
		return actionFrom;
	}

	public void setActionFrom(String actionFrom) {
		this.actionFrom = actionFrom;
	}

	public Boolean getHideAddBtn() {
		return hideAddBtn;
	}

	public void setHideAddBtn(Boolean hideAddBtn) {
		this.hideAddBtn = hideAddBtn;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
