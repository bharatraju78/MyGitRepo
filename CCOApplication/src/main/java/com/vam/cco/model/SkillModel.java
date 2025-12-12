package com.vam.cco.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class SkillModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8975047336893816954L;

	private Long skillId;
	
	private String skillName;
	
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	private String technologySolutionCenter;
	private List<String> techCenterList;
	
	public SkillModel() {
		techCenterList = new java.util.ArrayList<>();
	}
	
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
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

	public String getTechnologySolutionCenter() {
		return technologySolutionCenter;
	}

	public void setTechnologySolutionCenter(String technologySolutionCenter) {
		this.technologySolutionCenter = technologySolutionCenter;
	}
	@Override
	public String toString() {
		return "SkillModel [skillId=" + skillId + ", skillName=" + skillName + ", technologySolutionCenter=" + technologySolutionCenter + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ "]";
	}

	public List<String> getTechCenterList() {
		return techCenterList;
	}

	public void setTechCenterList(List<String> techCenterList) {
		this.techCenterList = techCenterList;
	}
	
	
}