package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OpportunityModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long opportunityId;
    private String oppNo;
    private String oppName;
    private Date oppCreatedDate;
    private Date oppStartDate;
    private Date oppEndDate;
    private Date oppClosureDate;
    private String oppClassification;
    private String oppTier;
    private String oppStatus;
    private String createdBy;
    private String modifiedBy;
    private Date createdDate;
    private Date modifiedDate;
    private List<String> oppStatusList;

    public OpportunityModel() {
    }

    public OpportunityModel(Long opportunityId, String oppNo, String oppName, Date oppCreatedDate,
            Date oppStartDate, Date oppEndDate, String oppStatus) {
        super();
        this.opportunityId = opportunityId;
        this.oppNo = oppNo;
        this.oppName = oppName;
        this.oppCreatedDate = oppCreatedDate;
        this.oppStartDate = oppStartDate;
        this.oppEndDate = oppEndDate;
        this.oppStatus = oppStatus;
    }

    public Long getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getOppNo() {
        return oppNo;
    }

    public void setOppNo(String oppNo) {
        this.oppNo = oppNo;
    }

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    public Date getOppCreatedDate() {
        return oppCreatedDate;
    }

    public void setOppCreatedDate(Date oppCreatedDate) {
        this.oppCreatedDate = oppCreatedDate;
    }

    public Date getOppStartDate() {
        return oppStartDate;
    }

    public void setOppStartDate(Date oppStartDate) {
        this.oppStartDate = oppStartDate;
    }

    public Date getOppClosureDate() {
        return oppClosureDate;
    }

    public void setOppClosureDate(Date oppClosureDate) {
        this.oppClosureDate = oppClosureDate;
    }

    public String getOppClassification() {
        return oppClassification;
    }

    public void setOppClassification(String oppClassification) {
        this.oppClassification = oppClassification;
    }

    public String getOppTier() {
        return oppTier;
    }

    public void setOppTier(String oppTier) {
        this.oppTier = oppTier;
    }

    public String getOppStatus() {
        return oppStatus;
    }

    public void setOppStatus(String oppStatus) {
        this.oppStatus = oppStatus;
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

    public List<String> getOppStatusList() {
        return oppStatusList;
    }

    public void setOppStatusList(List<String> oppStatusList) {
        this.oppStatusList = oppStatusList;
    }

    @Override
    public String toString() {
        return "OpportunityModel [opportunityId=" + opportunityId + ", oppNo=" + oppNo + ", oppName=" + oppName
                + ", oppCreatedDate=" + oppCreatedDate + ", oppStartDate=" + oppStartDate + ", oppClosureDate="
                + oppClosureDate + ", oppClassification=" + oppClassification + ", oppTier=" + oppTier
                + ", oppStatus=" + oppStatus + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
                + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
    }
}
