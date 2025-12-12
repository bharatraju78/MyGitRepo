package com.vam.cco.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EFFORT_INFO")
public class EffortInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;

    private String track;
    private String location;
    private String role;
    private String roleType;
    private String shift;
    private String employeeType;
    private String vmBand;
    private double rate;
    private double hoursPerMonth;

    private double yearOneJan;
    private double yearOneFeb;
    private double yearOneMar;
    private double yearOneApr;
    private double yearOneMay;
    private double yearOneJun;
    private double yearOneJul;
    private double yearOneAug;
    private double yearOneSep;
    private double yearOneOct;
    private double yearOneNov;
    private double yearOneDec;
    private double yearOneTotal;

    private double yearTwoJan;
    private double yearTwoFeb;
    private double yearTwoMar;
    private double yearTwoApr;
    private double yearTwoMay;
    private double yearTwoJun;
    private double yearTwoJul;
    private double yearTwoAug;
    private double yearTwoSep;
    private double yearTwoOct;
    private double yearTwoNov;
    private double yearTwoDec;
    private double yearTwoTotal;

    private double yearThreeJan;
    private double yearThreeFeb;
    private double yearThreeMar;
    private double yearThreeApr;
    private double yearThreeMay;
    private double yearThreeJun;
    private double yearThreeJul;
    private double yearThreeAug;
    private double yearThreeSep;
    private double yearThreeOct;
    private double yearThreeNov;
    private double yearThreeDec;
    private double yearThreeTotal;

    private double yearFourJan;
    private double yearFourFeb;
    private double yearFourMar;
    private double yearFourApr;
    private double yearFourMay;
    private double yearFourJun;
    private double yearFourJul;
    private double yearFourAug;
    private double yearFourSep;
    private double yearFourOct;
    private double yearFourNov;
    private double yearFourDec;
    private double yearFourTotal;

    private double overallTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EFFORT_DETAIL_ID")
    private EffortDetail effortDetail;

    public EffortInfo() {}

    public Long getInfoId() { return infoId; }
    public void setInfoId(Long infoId) { this.infoId = infoId; }

    public String getTrack() { return track; }
    public void setTrack(String track) { this.track = track; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getRoleType() { return roleType; }
    public void setRoleType(String roleType) { this.roleType = roleType; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public String getEmployeeType() { return employeeType; }
    public void setEmployeeType(String employeeType) { this.employeeType = employeeType; }

    public String getVmBand() { return vmBand; }
    public void setVmBand(String vmBand) { this.vmBand = vmBand; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public double getHoursPerMonth() { return hoursPerMonth; }
    public void setHoursPerMonth(double hoursPerMonth) { this.hoursPerMonth = hoursPerMonth; }

    // getters/setters for all year fields
    public double getYearOneJan() { return yearOneJan; }
    public void setYearOneJan(double yearOneJan) { this.yearOneJan = yearOneJan; }
    public double getYearOneFeb() { return yearOneFeb; }
    public void setYearOneFeb(double yearOneFeb) { this.yearOneFeb = yearOneFeb; }
    public double getYearOneMar() { return yearOneMar; }
    public void setYearOneMar(double yearOneMar) { this.yearOneMar = yearOneMar; }
    public double getYearOneApr() { return yearOneApr; }
    public void setYearOneApr(double yearOneApr) { this.yearOneApr = yearOneApr; }
    public double getYearOneMay() { return yearOneMay; }
    public void setYearOneMay(double yearOneMay) { this.yearOneMay = yearOneMay; }
    public double getYearOneJun() { return yearOneJun; }
    public void setYearOneJun(double yearOneJun) { this.yearOneJun = yearOneJun; }
    public double getYearOneJul() { return yearOneJul; }
    public void setYearOneJul(double yearOneJul) { this.yearOneJul = yearOneJul; }
    public double getYearOneAug() { return yearOneAug; }
    public void setYearOneAug(double yearOneAug) { this.yearOneAug = yearOneAug; }
    public double getYearOneSep() { return yearOneSep; }
    public void setYearOneSep(double yearOneSep) { this.yearOneSep = yearOneSep; }
    public double getYearOneOct() { return yearOneOct; }
    public void setYearOneOct(double yearOneOct) { this.yearOneOct = yearOneOct; }
    public double getYearOneNov() { return yearOneNov; }
    public void setYearOneNov(double yearOneNov) { this.yearOneNov = yearOneNov; }
    public double getYearOneDec() { return yearOneDec; }
    public void setYearOneDec(double yearOneDec) { this.yearOneDec = yearOneDec; }
    public double getYearOneTotal() { return yearOneTotal; }
    public void setYearOneTotal(double yearOneTotal) { this.yearOneTotal = yearOneTotal; }

    public double getYearTwoJan() { return yearTwoJan; }
    public void setYearTwoJan(double yearTwoJan) { this.yearTwoJan = yearTwoJan; }
    public double getYearTwoFeb() { return yearTwoFeb; }
    public void setYearTwoFeb(double yearTwoFeb) { this.yearTwoFeb = yearTwoFeb; }
    public double getYearTwoMar() { return yearTwoMar; }
    public void setYearTwoMar(double yearTwoMar) { this.yearTwoMar = yearTwoMar; }
    public double getYearTwoApr() { return yearTwoApr; }
    public void setYearTwoApr(double yearTwoApr) { this.yearTwoApr = yearTwoApr; }
    public double getYearTwoMay() { return yearTwoMay; }
    public void setYearTwoMay(double yearTwoMay) { this.yearTwoMay = yearTwoMay; }
    public double getYearTwoJun() { return yearTwoJun; }
    public void setYearTwoJun(double yearTwoJun) { this.yearTwoJun = yearTwoJun; }
    public double getYearTwoJul() { return yearTwoJul; }
    public void setYearTwoJul(double yearTwoJul) { this.yearTwoJul = yearTwoJul; }
    public double getYearTwoAug() { return yearTwoAug; }
    public void setYearTwoAug(double yearTwoAug) { this.yearTwoAug = yearTwoAug; }
    public double getYearTwoSep() { return yearTwoSep; }
    public void setYearTwoSep(double yearTwoSep) { this.yearTwoSep = yearTwoSep; }
    public double getYearTwoOct() { return yearTwoOct; }
    public void setYearTwoOct(double yearTwoOct) { this.yearTwoOct = yearTwoOct; }
    public double getYearTwoNov() { return yearTwoNov; }
    public void setYearTwoNov(double yearTwoNov) { this.yearTwoNov = yearTwoNov; }
    public double getYearTwoDec() { return yearTwoDec; }
    public void setYearTwoDec(double yearTwoDec) { this.yearTwoDec = yearTwoDec; }
    public double getYearTwoTotal() { return yearTwoTotal; }
    public void setYearTwoTotal(double yearTwoTotal) { this.yearTwoTotal = yearTwoTotal; }

    public double getYearThreeJan() { return yearThreeJan; }
    public void setYearThreeJan(double yearThreeJan) { this.yearThreeJan = yearThreeJan; }
    public double getYearThreeFeb() { return yearThreeFeb; }
    public void setYearThreeFeb(double yearThreeFeb) { this.yearThreeFeb = yearThreeFeb; }
    public double getYearThreeMar() { return yearThreeMar; }
    public void setYearThreeMar(double yearThreeMar) { this.yearThreeMar = yearThreeMar; }
    public double getYearThreeApr() { return yearThreeApr; }
    public void setYearThreeApr(double yearThreeApr) { this.yearThreeApr = yearThreeApr; }
    public double getYearThreeMay() { return yearThreeMay; }
    public void setYearThreeMay(double yearThreeMay) { this.yearThreeMay = yearThreeMay; }
    public double getYearThreeJun() { return yearThreeJun; }
    public void setYearThreeJun(double yearThreeJun) { this.yearThreeJun = yearThreeJun; }
    public double getYearThreeJul() { return yearThreeJul; }
    public void setYearThreeJul(double yearThreeJul) { this.yearThreeJul = yearThreeJul; }
    public double getYearThreeAug() { return yearThreeAug; }
    public void setYearThreeAug(double yearThreeAug) { this.yearThreeAug = yearThreeAug; }
    public double getYearThreeSep() { return yearThreeSep; }
    public void setYearThreeSep(double yearThreeSep) { this.yearThreeSep = yearThreeSep; }
    public double getYearThreeOct() { return yearThreeOct; }
    public void setYearThreeOct(double yearThreeOct) { this.yearThreeOct = yearThreeOct; }
    public double getYearThreeNov() { return yearThreeNov; }
    public void setYearThreeNov(double yearThreeNov) { this.yearThreeNov = yearThreeNov; }
    public double getYearThreeDec() { return yearThreeDec; }
    public void setYearThreeDec(double yearThreeDec) { this.yearThreeDec = yearThreeDec; }
    public double getYearThreeTotal() { return yearThreeTotal; }
    public void setYearThreeTotal(double yearThreeTotal) { this.yearThreeTotal = yearThreeTotal; }

    public double getYearFourJan() { return yearFourJan; }
    public void setYearFourJan(double yearFourJan) { this.yearFourJan = yearFourJan; }
    public double getYearFourFeb() { return yearFourFeb; }
    public void setYearFourFeb(double yearFourFeb) { this.yearFourFeb = yearFourFeb; }
    public double getYearFourMar() { return yearFourMar; }
    public void setYearFourMar(double yearFourMar) { this.yearFourMar = yearFourMar; }
    public double getYearFourApr() { return yearFourApr; }
    public void setYearFourApr(double yearFourApr) { this.yearFourApr = yearFourApr; }
    public double getYearFourMay() { return yearFourMay; }
    public void setYearFourMay(double yearFourMay) { this.yearFourMay = yearFourMay; }
    public double getYearFourJun() { return yearFourJun; }
    public void setYearFourJun(double yearFourJun) { this.yearFourJun = yearFourJun; }
    public double getYearFourJul() { return yearFourJul; }
    public void setYearFourJul(double yearFourJul) { this.yearFourJul = yearFourJul; }
    public double getYearFourAug() { return yearFourAug; }
    public void setYearFourAug(double yearFourAug) { this.yearFourAug = yearFourAug; }
    public double getYearFourSep() { return yearFourSep; }
    public void setYearFourSep(double yearFourSep) { this.yearFourSep = yearFourSep; }
    public double getYearFourOct() { return yearFourOct; }
    public void setYearFourOct(double yearFourOct) { this.yearFourOct = yearFourOct; }
    public double getYearFourNov() { return yearFourNov; }
    public void setYearFourNov(double yearFourNov) { this.yearFourNov = yearFourNov; }
    public double getYearFourDec() { return yearFourDec; }
    public void setYearFourDec(double yearFourDec) { this.yearFourDec = yearFourDec; }
    public double getYearFourTotal() { return yearFourTotal; }
    public void setYearFourTotal(double yearFourTotal) { this.yearFourTotal = yearFourTotal; }

    public double getOverallTotal() { return overallTotal; }
    public void setOverallTotal(double overallTotal) { this.overallTotal = overallTotal; }

    public com.vam.cco.dao.entity.EffortDetail getEffortDetail() { return effortDetail; }
    public void setEffortDetail(com.vam.cco.dao.entity.EffortDetail effortDetail) { this.effortDetail = effortDetail; }
}
