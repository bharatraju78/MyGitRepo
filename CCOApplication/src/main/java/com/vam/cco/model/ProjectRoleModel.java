package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;

public class ProjectRoleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118102817690988339L;

	private Long projectRoleId;

	private String roleName;
	private Integer orderNo;
	
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	
	public ProjectRoleModel() {}
	
	public ProjectRoleModel(Long projectRoleId, String roleName) {
		this.projectRoleId = projectRoleId;
		this.roleName = roleName;
	}
	
	public ProjectRoleModel(Long projectRoleId, String roleName, Integer orderNo) {
		this.projectRoleId = projectRoleId;
		this.roleName = roleName;
		this.orderNo = orderNo;
	}
	
	public Long getProjectRoleId() {
		return projectRoleId;
	}
	public void setProjectRoleId(Long projectRoleId) {
		this.projectRoleId = projectRoleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "ProjectRoleModel [projectRoleId=" + projectRoleId + ", roleName=" + roleName + ", orderNo=" + orderNo
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}
	
}