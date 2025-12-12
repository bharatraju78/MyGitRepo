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
@Table(name = "RESOURCE_INFO")
public class ResourceInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8131026025122968366L;
	
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
	
	private int yearOneJan;
	private int yearOneFeb;
	private int yearOneMar;
	private int yearOneApr;
	private int yearOneMay;
	private int yearOneJun;
	private int yearOneJul;
	private int yearOneAug;
	private int yearOneSep;
	private int yearOneOct;
	private int yearOneNov;
	private int yearOneDec;
	private int yearOneTotal;
	
	private int yearTwoJan;
	private int yearTwoFeb;
	private int yearTwoMar;
	private int yearTwoApr;
	private int yearTwoMay;
	private int yearTwoJun;
	private int yearTwoJul;
	private int yearTwoAug;
	private int yearTwoSep;
	private int yearTwoOct;
	private int yearTwoNov;
	private int yearTwoDec;
	private int yearTwoTotal;
	
	private int yearThreeJan;
	private int yearThreeFeb;
	private int yearThreeMar;
	private int yearThreeApr;
	private int yearThreeMay;
	private int yearThreeJun;
	private int yearThreeJul;
	private int yearThreeAug;
	private int yearThreeSep;
	private int yearThreeOct;
	private int yearThreeNov;
	private int yearThreeDec;
	private int yearThreeTotal;
	
	private int yearFourJan;
	private int yearFourFeb;
	private int yearFourMar;
	private int yearFourApr;
	private int yearFourMay;
	private int yearFourJun;
	private int yearFourJul;
	private int yearFourAug;
	private int yearFourSep;
	private int yearFourOct;
	private int yearFourNov;
	private int yearFourDec;
	private int yearFourTotal;
	
	private int overallTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RES_DETAIL_ID") // Foreign key column in the ResourceDetail table
	private ResourceDetail resourceDetail;
	
	
	public ResourceInfo() {
		// TODO Auto-generated constructor stub
	}


	public Long getInfoId() {
		return infoId;
	}


	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}


	public String getTrack() {
		return track;
	}


	public void setTrack(String track) {
		this.track = track;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getRoleType() {
		return roleType;
	}


	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}


	public String getShift() {
		return shift;
	}


	public void setShift(String shift) {
		this.shift = shift;
	}


	public String getEmployeeType() {
		return employeeType;
	}


	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}


	public String getVmBand() {
		return vmBand;
	}


	public void setVmBand(String vmBand) {
		this.vmBand = vmBand;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public double getHoursPerMonth() {
		return hoursPerMonth;
	}


	public void setHoursPerMonth(double hoursPerMonth) {
		this.hoursPerMonth = hoursPerMonth;
	}


	public int getYearOneJan() {
		return yearOneJan;
	}


	public void setYearOneJan(int yearOneJan) {
		this.yearOneJan = yearOneJan;
	}


	public int getYearOneFeb() {
		return yearOneFeb;
	}


	public void setYearOneFeb(int yearOneFeb) {
		this.yearOneFeb = yearOneFeb;
	}


	public int getYearOneMar() {
		return yearOneMar;
	}


	public void setYearOneMar(int yearOneMar) {
		this.yearOneMar = yearOneMar;
	}


	public int getYearOneApr() {
		return yearOneApr;
	}


	public void setYearOneApr(int yearOneApr) {
		this.yearOneApr = yearOneApr;
	}


	public int getYearOneMay() {
		return yearOneMay;
	}


	public void setYearOneMay(int yearOneMay) {
		this.yearOneMay = yearOneMay;
	}


	public int getYearOneJun() {
		return yearOneJun;
	}


	public void setYearOneJun(int yearOneJun) {
		this.yearOneJun = yearOneJun;
	}


	public int getYearOneJul() {
		return yearOneJul;
	}


	public void setYearOneJul(int yearOneJul) {
		this.yearOneJul = yearOneJul;
	}


	public int getYearOneAug() {
		return yearOneAug;
	}


	public void setYearOneAug(int yearOneAug) {
		this.yearOneAug = yearOneAug;
	}


	public int getYearOneSep() {
		return yearOneSep;
	}


	public void setYearOneSep(int yearOneSep) {
		this.yearOneSep = yearOneSep;
	}


	public int getYearOneOct() {
		return yearOneOct;
	}


	public void setYearOneOct(int yearOneOct) {
		this.yearOneOct = yearOneOct;
	}


	public int getYearOneNov() {
		return yearOneNov;
	}


	public void setYearOneNov(int yearOneNov) {
		this.yearOneNov = yearOneNov;
	}


	public int getYearOneDec() {
		return yearOneDec;
	}


	public void setYearOneDec(int yearOneDec) {
		this.yearOneDec = yearOneDec;
	}


	public int getYearOneTotal() {
		return yearOneTotal;
	}


	public void setYearOneTotal(int yearOneTotal) {
		this.yearOneTotal = yearOneTotal;
	}


	public int getYearTwoJan() {
		return yearTwoJan;
	}


	public void setYearTwoJan(int yearTwoJan) {
		this.yearTwoJan = yearTwoJan;
	}


	public int getYearTwoFeb() {
		return yearTwoFeb;
	}


	public void setYearTwoFeb(int yearTwoFeb) {
		this.yearTwoFeb = yearTwoFeb;
	}


	public int getYearTwoMar() {
		return yearTwoMar;
	}


	public void setYearTwoMar(int yearTwoMar) {
		this.yearTwoMar = yearTwoMar;
	}


	public int getYearTwoApr() {
		return yearTwoApr;
	}


	public void setYearTwoApr(int yearTwoApr) {
		this.yearTwoApr = yearTwoApr;
	}


	public int getYearTwoMay() {
		return yearTwoMay;
	}


	public void setYearTwoMay(int yearTwoMay) {
		this.yearTwoMay = yearTwoMay;
	}


	public int getYearTwoJun() {
		return yearTwoJun;
	}


	public void setYearTwoJun(int yearTwoJun) {
		this.yearTwoJun = yearTwoJun;
	}


	public int getYearTwoJul() {
		return yearTwoJul;
	}


	public void setYearTwoJul(int yearTwoJul) {
		this.yearTwoJul = yearTwoJul;
	}


	public int getYearTwoAug() {
		return yearTwoAug;
	}


	public void setYearTwoAug(int yearTwoAug) {
		this.yearTwoAug = yearTwoAug;
	}


	public int getYearTwoSep() {
		return yearTwoSep;
	}


	public void setYearTwoSep(int yearTwoSep) {
		this.yearTwoSep = yearTwoSep;
	}


	public int getYearTwoOct() {
		return yearTwoOct;
	}


	public void setYearTwoOct(int yearTwoOct) {
		this.yearTwoOct = yearTwoOct;
	}


	public int getYearTwoNov() {
		return yearTwoNov;
	}


	public void setYearTwoNov(int yearTwoNov) {
		this.yearTwoNov = yearTwoNov;
	}


	public int getYearTwoDec() {
		return yearTwoDec;
	}


	public void setYearTwoDec(int yearTwoDec) {
		this.yearTwoDec = yearTwoDec;
	}


	public int getYearTwoTotal() {
		return yearTwoTotal;
	}


	public void setYearTwoTotal(int yearTwoTotal) {
		this.yearTwoTotal = yearTwoTotal;
	}


	public int getYearThreeJan() {
		return yearThreeJan;
	}


	public void setYearThreeJan(int yearThreeJan) {
		this.yearThreeJan = yearThreeJan;
	}


	public int getYearThreeFeb() {
		return yearThreeFeb;
	}


	public void setYearThreeFeb(int yearThreeFeb) {
		this.yearThreeFeb = yearThreeFeb;
	}


	public int getYearThreeMar() {
		return yearThreeMar;
	}


	public void setYearThreeMar(int yearThreeMar) {
		this.yearThreeMar = yearThreeMar;
	}


	public int getYearThreeApr() {
		return yearThreeApr;
	}


	public void setYearThreeApr(int yearThreeApr) {
		this.yearThreeApr = yearThreeApr;
	}


	public int getYearThreeMay() {
		return yearThreeMay;
	}


	public void setYearThreeMay(int yearThreeMay) {
		this.yearThreeMay = yearThreeMay;
	}


	public int getYearThreeJun() {
		return yearThreeJun;
	}


	public void setYearThreeJun(int yearThreeJun) {
		this.yearThreeJun = yearThreeJun;
	}


	public int getYearThreeJul() {
		return yearThreeJul;
	}


	public void setYearThreeJul(int yearThreeJul) {
		this.yearThreeJul = yearThreeJul;
	}


	public int getYearThreeAug() {
		return yearThreeAug;
	}


	public void setYearThreeAug(int yearThreeAug) {
		this.yearThreeAug = yearThreeAug;
	}


	public int getYearThreeSep() {
		return yearThreeSep;
	}


	public void setYearThreeSep(int yearThreeSep) {
		this.yearThreeSep = yearThreeSep;
	}


	public int getYearThreeOct() {
		return yearThreeOct;
	}


	public void setYearThreeOct(int yearThreeOct) {
		this.yearThreeOct = yearThreeOct;
	}


	public int getYearThreeNov() {
		return yearThreeNov;
	}


	public void setYearThreeNov(int yearThreeNov) {
		this.yearThreeNov = yearThreeNov;
	}


	public int getYearThreeDec() {
		return yearThreeDec;
	}


	public void setYearThreeDec(int yearThreeDec) {
		this.yearThreeDec = yearThreeDec;
	}


	public int getYearThreeTotal() {
		return yearThreeTotal;
	}


	public void setYearThreeTotal(int yearThreeTotal) {
		this.yearThreeTotal = yearThreeTotal;
	}


	public int getYearFourJan() {
		return yearFourJan;
	}


	public void setYearFourJan(int yearFourJan) {
		this.yearFourJan = yearFourJan;
	}


	public int getYearFourFeb() {
		return yearFourFeb;
	}


	public void setYearFourFeb(int yearFourFeb) {
		this.yearFourFeb = yearFourFeb;
	}


	public int getYearFourMar() {
		return yearFourMar;
	}


	public void setYearFourMar(int yearFourMar) {
		this.yearFourMar = yearFourMar;
	}


	public int getYearFourApr() {
		return yearFourApr;
	}


	public void setYearFourApr(int yearFourApr) {
		this.yearFourApr = yearFourApr;
	}


	public int getYearFourMay() {
		return yearFourMay;
	}


	public void setYearFourMay(int yearFourMay) {
		this.yearFourMay = yearFourMay;
	}


	public int getYearFourJun() {
		return yearFourJun;
	}


	public void setYearFourJun(int yearFourJun) {
		this.yearFourJun = yearFourJun;
	}


	public int getYearFourJul() {
		return yearFourJul;
	}


	public void setYearFourJul(int yearFourJul) {
		this.yearFourJul = yearFourJul;
	}


	public int getYearFourAug() {
		return yearFourAug;
	}


	public void setYearFourAug(int yearFourAug) {
		this.yearFourAug = yearFourAug;
	}


	public int getYearFourSep() {
		return yearFourSep;
	}


	public void setYearFourSep(int yearFourSep) {
		this.yearFourSep = yearFourSep;
	}


	public int getYearFourOct() {
		return yearFourOct;
	}


	public void setYearFourOct(int yearFourOct) {
		this.yearFourOct = yearFourOct;
	}


	public int getYearFourNov() {
		return yearFourNov;
	}


	public void setYearFourNov(int yearFourNov) {
		this.yearFourNov = yearFourNov;
	}


	public int getYearFourDec() {
		return yearFourDec;
	}


	public void setYearFourDec(int yearFourDec) {
		this.yearFourDec = yearFourDec;
	}


	public int getYearFourTotal() {
		return yearFourTotal;
	}


	public void setYearFourTotal(int yearFourTotal) {
		this.yearFourTotal = yearFourTotal;
	}


	public int getOverallTotal() {
		return overallTotal;
	}


	public void setOverallTotal(int overallTotal) {
		this.overallTotal = overallTotal;
	}


	public ResourceDetail getResourceDetail() {
		return resourceDetail;
	}


	public void setResourceDetail(ResourceDetail resourceDetail) {
		this.resourceDetail = resourceDetail;
	}

}
