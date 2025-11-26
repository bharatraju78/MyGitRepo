package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AccountModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1271540149911377805L;
	
	private Long accountId;
	private String accNo;
	private String accName;
	private Date accCreatedDate;
	private Date accStartDate;
	private Date accEndDate;
	private Date accClosureDate;
	private String accClassification;
	private String accTier;
	private String accStatus;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	private List<String> accStatusList;
	
	public AccountModel() {
	}
	
	public AccountModel(Long accountId, String accNo, String accName, Date accCreatedDate,
			Date accStartDate, Date accEndDate, String accStatus) {
		super();
		this.accountId = accountId;
		this.accNo = accNo;
		this.accName = accName;
		this.accCreatedDate = accCreatedDate;
		this.accStartDate = accStartDate;
		this.accEndDate = accEndDate;
		this.accStatus = accStatus;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Date getAccCreatedDate() {
		return accCreatedDate;
	}
	public void setAccCreatedDate(Date accCreatedDate) {
		this.accCreatedDate = accCreatedDate;
	}
	public Date getAccStartDate() {
		return accStartDate;
	}
	public void setAccStartDate(Date accStartDate) {
		this.accStartDate = accStartDate;
	}
	public Date getAccClosureDate() {
		return accClosureDate;
	}
	public void setAccClosureDate(Date accClosureDate) {
		this.accClosureDate = accClosureDate;
	}
	public String getAccClassification() {
		return accClassification;
	}
	public void setAccClassification(String accClassification) {
		this.accClassification = accClassification;
	}
	public String getAccTier() {
		return accTier;
	}
	public void setAccTier(String accTier) {
		this.accTier = accTier;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public List<String> getAccStatusList() {
		return accStatusList;
	}
	public void setAccStatusList(List<String> accStatusList) {
		this.accStatusList = accStatusList;
	}
	
	@Override
	public String toString() {
		return "AccountModel [accountId=" + accountId + ", accNo=" + accNo + ", accName=" + accName
				+ ", accCreatedDate=" + accCreatedDate + ", accStartDate=" + accStartDate + ", accClosureDate="
				+ accClosureDate + ", accClassification=" + accClassification + ", accTier=" + accTier
				+ ", accStatus=" + accStatus + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}

	public Date getAccEndDate() {
		return accEndDate;
	}

	public void setAccEndDate(Date accEndDate) {
		this.accEndDate = accEndDate;
	}
}
