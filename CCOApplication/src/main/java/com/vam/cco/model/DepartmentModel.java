/**
 * 
 */
package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bharat“Sai”Nallapara
 *
 */
public class DepartmentModel implements Serializable {

	private Long departmentId;
	private String departmentCode;
	private String departmentName;
	private String departmentStatus;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	private List<String> departmentStatusList;
	
	/**
	 * 
	 */
	public DepartmentModel() {
		// TODO Auto-generated constructor stub
	}

	// Add a constructor used by JPQL new projections
	public DepartmentModel(Long departmentId, String departmentCode, String departmentName, String departmentStatus,
						String createdBy, String modifiedBy, Date createdDate, Date modifiedDate) {
		this.departmentId = departmentId;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.departmentStatus = departmentStatus;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentStatus() {
		return departmentStatus;
	}

	public void setDepartmentStatus(String departmentStatus) {
		this.departmentStatus = departmentStatus;
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

	public List<String> getDepartmentStatusList() {
		return departmentStatusList;
	}

	public void setDepartmentStatusList(List<String> departmentStatusList) {
		this.departmentStatusList = departmentStatusList;
	}

}