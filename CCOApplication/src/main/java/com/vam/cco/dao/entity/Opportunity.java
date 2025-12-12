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
@Table(name = "OPPORTUNITY")
public class Opportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opportunityId;

    private String oppNo;
    private String oppName;
    private Date oppCreatedDate;
    private Date oppStartDate;
    private Date oppEndDate;
    private String oppClassification;
    private String oppTier;
    private String oppStatus;

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

    public Opportunity() {
        super();
    }

    public Opportunity(Long opportunityId, String oppName) {
        this.opportunityId = opportunityId;
        this.oppName = oppName;
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

    public Date getOppEndDate() {
        return oppEndDate;
    }

    public void setOppEndDate(Date oppEndDate) {
        this.oppEndDate = oppEndDate;
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

    @Override
    public String toString() {
        return "Opportunity [opportunityId=" + opportunityId + ", oppNo=" + oppNo + ", oppName=" + oppName
                + ", oppCreatedDate=" + oppCreatedDate + ", oppStartDate=" + oppStartDate + ", oppEndDate="
                + oppEndDate + ", oppClassification=" + oppClassification + ", oppTier=" + oppTier + ", oppStatus="
                + oppStatus + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDate="
                + createdDate + ", modifiedDate=" + modifiedDate + ", portfolio=" + portfolio + "]";
    }
}
