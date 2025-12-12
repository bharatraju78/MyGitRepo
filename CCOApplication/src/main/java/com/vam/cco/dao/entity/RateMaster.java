package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "RATE_MASTER")
public class RateMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;

    // Identifier is rateId (primary key)
    // Technology / Service Center stored before skill
    private String technologyServiceCenter;

    private String accountName;
    private String skill;
    private String role;

    private BigDecimal rateAmount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effectiveFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effectiveTo;

    private String rateStatus;

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

    public RateMaster() { super(); }

    public RateMaster(Long rateId) {
        this.rateId = rateId;
    }

    public Long getRateId() { return rateId; }
    public void setRateId(Long rateId) { this.rateId = rateId; }

    public String getTechnologyServiceCenter() { return technologyServiceCenter; }
    public void setTechnologyServiceCenter(String technologyServiceCenter) { this.technologyServiceCenter = technologyServiceCenter; }

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

    @Override
    public String toString() {
        return "RateMaster [rateId=" + rateId + ", technologyServiceCenter=" + technologyServiceCenter + ", accountName=" + accountName
                + ", skill=" + skill + ", role=" + role + ", rateAmount=" + rateAmount + ", effectiveFrom=" + effectiveFrom
                 + ", effectiveTo=" + effectiveTo + ", rateStatus=" + rateStatus + ", createdBy=" + createdBy
                 + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
    }
}