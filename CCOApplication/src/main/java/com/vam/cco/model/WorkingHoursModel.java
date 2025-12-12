package com.vam.cco.model;

import java.io.Serializable;
import java.util.Date;

public class WorkingHoursModel implements Serializable {

    private Long workingHoursId;
    private String location;
    private double hoursPerMonth;
    private String createdBy;
    private String modifiedBy;
    private Date createdDate;
    private Date modifiedDate;

    public WorkingHoursModel() {
    }

    public WorkingHoursModel(Long workingHoursId, String location, double hoursPerMonth) {
        this.workingHoursId = workingHoursId;
        this.location = location;
        this.hoursPerMonth = hoursPerMonth;
    }

    public Long getWorkingHoursId() {
        return workingHoursId;
    }

    public void setWorkingHoursId(Long workingHoursId) {
        this.workingHoursId = workingHoursId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getHoursPerMonth() {
        return hoursPerMonth;
    }

    public void setHoursPerMonth(double hoursPerMonth) {
        this.hoursPerMonth = hoursPerMonth;
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
}
