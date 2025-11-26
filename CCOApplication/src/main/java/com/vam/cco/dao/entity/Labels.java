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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "labels")
public class Labels implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8717032037950533745L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelId;
	
	private String labelName;
	
	private Integer orderNo;
	
	private String labelType;
	
	@Transient
	private Boolean isSelected = null;
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	@Column(name = "modified_by", nullable = false)
	private String modifiedBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "modified_date", nullable = false)
	private Date modifiedDate;
	
    @ManyToMany(mappedBy = "labels", fetch = FetchType.LAZY)
	private Set<ProjectRole> projectRoles;
	
	public Long getLabelId() {
		return labelId;
	}
	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	public String getLabelType() {
		return labelType;
	}
	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public Set<ProjectRole> getProjectRoles() {
		return projectRoles;
	}
	public void setProjectRoles(Set<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}
	@Override
	public String toString() {
		return "Labels [labelId=" + labelId + ", labelName=" + labelName + ", orderNo=" + orderNo + ", labelType="
				+ labelType + ", isSelected=" + isSelected + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", projectRoles=" + projectRoles
				+ "]";
	}
	
}