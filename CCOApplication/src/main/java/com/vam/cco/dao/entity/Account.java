package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {				

	/**
	 * 
	 */
	private static final long serialVersionUID = 8630971548948413580L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
	
	private String accNo;
	private String accName;
	private Date accCreatedDate;
	private Date accStartDate;
	private Date accEndDate;
	private String accClassification;
	private String accTier;
	private String accStatus;
	
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
	
	@ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
	
	public Account() {
		super();
	}
	
	public Account(Long accountId, String accName) {
		this.accountId = accountId;
		this.accName = accName;
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

	public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

	public Date getAccEndDate() {
		return accEndDate;
	}

	public void setAccEndDate(Date accEndDate) {
		this.accEndDate = accEndDate;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accNo=" + accNo + ", accName=" + accName + ", accCreatedDate="
				+ accCreatedDate + ", accStartDate=" + accStartDate + ", accEndDate=" + accEndDate
				+ ", accClassification=" + accClassification + ", accTier=" + accTier + ", accStatus=" + accStatus
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", portfolio=" + portfolio + "]";
	}
	
	
	
}