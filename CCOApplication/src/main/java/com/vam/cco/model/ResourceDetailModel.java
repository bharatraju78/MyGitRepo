package com.vam.cco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

public class ResourceDetailModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long resDetailId;
	private OnsiteTotalModel onsiteTotal;
	private OffshoreTotalModel offshoreTotal;
	private ShiftAllowanceCostModel shiftAllowanceCost;
	@Valid
	private List<ResourceInfoModel> resourceInfos;
	@PositiveOrZero(message = "Total team size must be zero or positive")
	private double totalTeamSize;
	private double totalOnsiteTeamSize;
	private double totalOffshoreTeamSize;
	private Long teaEstimateId; // parent reference
	
	private List<String> locationList;
	private List<String> roleTypeList;
	private List<String> shiftList;
	private List<String> employeeTypeList;

	public ResourceDetailModel() {
		this.resourceInfos = new ArrayList<ResourceInfoModel>();
		this.onsiteTotal = new OnsiteTotalModel();
		this.offshoreTotal = new OffshoreTotalModel();
		this.shiftAllowanceCost = new ShiftAllowanceCostModel();
		
		locationList = new ArrayList<String>();
		roleTypeList = new ArrayList<String>();
		shiftList = new ArrayList<String>();
		employeeTypeList = new ArrayList<String>();
	}

	public Long getResDetailId() {
		return resDetailId;
	}

	public void setResDetailId(Long resDetailId) {
		this.resDetailId = resDetailId;
	}

	public OnsiteTotalModel getOnsiteTotal() {
		return onsiteTotal;
	}

	public void setOnsiteTotal(OnsiteTotalModel onsiteTotal) {
		this.onsiteTotal = onsiteTotal;
	}

	public OffshoreTotalModel getOffshoreTotal() {
		return offshoreTotal;
	}

	public void setOffshoreTotal(OffshoreTotalModel offshoreTotal) {
		this.offshoreTotal = offshoreTotal;
	}

	public ShiftAllowanceCostModel getShiftAllowanceCost() {
		return shiftAllowanceCost;
	}

	public void setShiftAllowanceCost(ShiftAllowanceCostModel shiftAllowanceCost) {
		this.shiftAllowanceCost = shiftAllowanceCost;
	}

	public List<ResourceInfoModel> getResourceInfos() {
		return resourceInfos;
	}

	public void setResourceInfos(List<ResourceInfoModel> resourceInfos) {
		this.resourceInfos = resourceInfos;
	}

	public double getTotalTeamSize() {
		return totalTeamSize;
	}

	public void setTotalTeamSize(double totalTeamSize) {
		this.totalTeamSize = totalTeamSize;
	}

	public double getTotalOnsiteTeamSize() {
		return totalOnsiteTeamSize;
	}

	public void setTotalOnsiteTeamSize(double totalOnsiteTeamSize) {
		this.totalOnsiteTeamSize = totalOnsiteTeamSize;
	}

	public double getTotalOffshoreTeamSize() {
		return totalOffshoreTeamSize;
	}

	public void setTotalOffshoreTeamSize(double totalOffshoreTeamSize) {
		this.totalOffshoreTeamSize = totalOffshoreTeamSize;
	}

	public Long getTeaEstimateId() {
		return teaEstimateId;
	}

	public void setTeaEstimateId(Long teaEstimateId) {
		this.teaEstimateId = teaEstimateId;
	}
	
	public List<String> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<String> locationList) {
		this.locationList = locationList;
	}

	public List<String> getRoleTypeList() {
		return roleTypeList;
	}

	public void setRoleTypeList(List<String> roleTypeList) {
		this.roleTypeList = roleTypeList;
	}

	public List<String> getShiftList() {
		return shiftList;
	}

	public void setShiftList(List<String> shiftList) {
		this.shiftList = shiftList;
	}

	public List<String> getEmployeeTypeList() {
		return employeeTypeList;
	}

	public void setEmployeeTypeList(List<String> employeeTypeList) {
		this.employeeTypeList = employeeTypeList;
	}

}