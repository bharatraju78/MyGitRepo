package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PracticeModel implements Serializable {

    private Long practiceId;
    private String practiceCode;
    private String practiceName;
    private String practiceStatus;
    private String createdBy;
    private String modifiedBy;
    private Date createdDate;
    private Date modifiedDate;
    private List<String> practiceStatusList;

    public PracticeModel() {
    }

    public PracticeModel(Long practiceId, String practiceCode, String practiceName, String practiceStatus,
            String createdBy, String modifiedBy, Date createdDate, Date modifiedDate) {
        this.practiceId = practiceId;
        this.practiceCode = practiceCode;
        this.practiceName = practiceName;
        this.practiceStatus = practiceStatus;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Long practiceId) {
        this.practiceId = practiceId;
    }

    public String getPracticeCode() {
        return practiceCode;
    }

    public void setPracticeCode(String practiceCode) {
        this.practiceCode = practiceCode;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public String getPracticeStatus() {
        return practiceStatus;
    }

    public void setPracticeStatus(String practiceStatus) {
        this.practiceStatus = practiceStatus;
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

    public List<String> getPracticeStatusList() {
        return practiceStatusList;
    }

    public void setPracticeStatusList(List<String> practiceStatusList) {
        this.practiceStatusList = practiceStatusList;
    }
}
