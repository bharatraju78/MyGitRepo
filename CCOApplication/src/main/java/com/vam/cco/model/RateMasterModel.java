package com.vam.cco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RateMasterModel implements Serializable {
    private static final long serialVersionUID = -3711326887604569835L;
	private Long rateId;
    private String technologyServiceCenter;

    private String account;
    private String accountName;
    private String skill;
    private String role;

    private BigDecimal rateAmount;
    private Date effectiveFrom;
    private Date effectiveTo;

    private String rateStatus;

    private String createdBy;
    private String modifiedBy;
    private Date createdDate;
    private Date modifiedDate;
    
    private List<String> techCenterList;
    private List<String> accountList;
    private List<String> projectRoleList;

    public RateMasterModel() {
    	techCenterList = new java.util.ArrayList<>();
    	accountList = new java.util.ArrayList<>();
    	projectRoleList = new java.util.ArrayList<>();
    }

    public RateMasterModel(Long rateId) {
        this.rateId = rateId;
    }

    // Getters and setters
    public Long getRateId() { return rateId; }
    public void setRateId(Long rateId) { this.rateId = rateId; }
    public String getTechnologyServiceCenter() { return technologyServiceCenter; }
    public void setTechnologyServiceCenter(String technologyServiceCenter) { this.technologyServiceCenter = technologyServiceCenter; }
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    public String getSkill() { return skill; }
    public void setSkill(String skill) { this.skill = skill; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public BigDecimal getRateAmount() { return rateAmount; }
    public void setRateAmount(BigDecimal rateAmount) { this.rateAmount = rateAmount; }
    public Date getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(Date effectiveFrom) { this.effectiveFrom = effectiveFrom; }
    public Date getEffectiveTo() { return effectiveTo; }
    public void setEffectiveTo(Date effectiveTo) { this.effectiveTo = effectiveTo; }
    public String getRateStatus() { return rateStatus; }
    public void setRateStatus(String rateStatus) { this.rateStatus = rateStatus; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public Date getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(Date modifiedDate) { this.modifiedDate = modifiedDate; }
	public List<String> getTechCenterList() { return techCenterList; }
	public void setTechCenterList(List<String> techCenterList) { this.techCenterList = techCenterList; }
	public List<String> getAccountList() { return accountList; }
	public void setAccountList(List<String> accountList) { this.accountList = accountList; }
	public List<String> getProjectRoleList() { return projectRoleList; }
	public void setProjectRoleList(List<String> projectRoleList) { this.projectRoleList = projectRoleList; }
}