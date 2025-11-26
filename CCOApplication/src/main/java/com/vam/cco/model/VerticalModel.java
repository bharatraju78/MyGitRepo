package com.vam.cco.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class VerticalModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1358648351267030246L;
	
	private Long verticalId;
	
	private String verticalName;
	
	private String createdBy;
	private String modifiedBy;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	
	public VerticalModel() {}
	
	public VerticalModel(Long verticalId, String verticalName) {
		this.verticalId = verticalId;
		this.verticalName = verticalName;
	}
	
	public Long getVerticalId() {
		return verticalId;
	}
	public void setVerticalId(Long verticalId) {
		this.verticalId = verticalId;
	}
	public String getVerticalName() {
		return verticalName;
	}
	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
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
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "VerticalModel [verticalId=" + verticalId + ", verticalName=" + verticalName + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ "]";
	}
}
