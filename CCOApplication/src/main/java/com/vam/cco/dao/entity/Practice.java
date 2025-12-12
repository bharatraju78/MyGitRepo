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
@Table(name = "PRACTICE")
public class Practice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long practiceId;

    private String practiceCode;
    private String practiceName;
    private String practiceStatus;

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

    public Practice() {
        super();
    }

    public Practice(Long practiceId, String practiceCode, String practiceName) {
        this.practiceId = practiceId;
        this.practiceCode = practiceCode;
        this.practiceName = practiceName;
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

    @Override
    public String toString() {
        return "Practice [practiceId=" + practiceId + ", practiceCode=" + practiceCode + ", practiceName="
                + practiceName + ", practiceStatus=" + practiceStatus + ", createdBy=" + createdBy
                + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
                + "]";
    }
}
