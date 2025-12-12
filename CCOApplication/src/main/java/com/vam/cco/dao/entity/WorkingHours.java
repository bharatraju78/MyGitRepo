package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "WORKING_HOURS")
public class WorkingHours implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workingHoursId;

    private String location;

    private double hoursPerMonth;

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

    public WorkingHours() {
        super();
    }

    public WorkingHours(Long workingHoursId, String location, double hoursPerMonth) {
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

    @Override
    public String toString() {
        return "WorkingHours [workingHoursId=" + workingHoursId + ", location=" + location + ", hoursPerMonth=" + hoursPerMonth
                + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate
                + ", modifiedDate=" + modifiedDate + "]";
    }
}
