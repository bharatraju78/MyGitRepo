package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "project_role")
public class ProjectRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 935124982195423603L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectRoleId;
	
	private String roleName;
	private Integer orderNo;
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	@Column(name = "modified_by", nullable = false)
	private String modifiedBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "modified_date", nullable = false)
	private Date modifiedDate;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "project_role_labels", joinColumns = @JoinColumn(name = "project_role_Id"), 
		inverseJoinColumns = @JoinColumn(name = "label_id"))
	private Set<Labels> labels;
	
	public ProjectRole() {}
	
	public ProjectRole(Long projectRoleId, String roleName) {
		this.projectRoleId = projectRoleId;
		this.roleName = roleName;
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
		return "ProjectRole [projectRoleId=" + projectRoleId + ", roleName=" + roleName + ", orderNo=" + orderNo
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	public Set<Labels> getLabels() {
		return labels;
	}

	public void setLabels(Set<Labels> labels) {
		this.labels = labels;
	}
}