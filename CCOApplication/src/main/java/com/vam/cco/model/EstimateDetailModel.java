package com.vam.cco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class EstimateDetailModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long estDetailId;
	@NotBlank(message = "Engagement name is required")
	private String engagementName;
	@PositiveOrZero(message = "Total effort must be zero or positive")
	private double totalEffortInPersonHours;
	private double totalProjectManagementEffort;
	private double totalBusinessAnalysisEffort;
	private double totalDevelopmentEffort;
	private double totalQualityAssuranceEffort;
	private double totalAssetsAndAcceleratorsEffort;
	private double totalGenAIEffort;
	private double totalPersonHours;
	private double totalPersonDays;
	private double totalPersonMonths;
	private Long version;
	private double totalThreePointEstimate;

	@Valid
	private List<EstimationItemModel> estimationItems;
	private Long teaEstimateId; // reference to parent

	public EstimateDetailModel() {
		this.estimationItems = new ArrayList<EstimationItemModel>();
	}

	public Long getEstDetailId() {
		return estDetailId;
	}

	public void setEstDetailId(Long estDetailId) {
		this.estDetailId = estDetailId;
	}

	public String getEngagementName() {
		return engagementName;
	}

	public void setEngagementName(String engagementName) {
		this.engagementName = engagementName;
	}

	public double getTotalEffortInPersonHours() {
		return totalEffortInPersonHours;
	}

	public void setTotalEffortInPersonHours(double totalEffortInPersonHours) {
		this.totalEffortInPersonHours = totalEffortInPersonHours;
	}

	public double getTotalProjectManagementEffort() {
		return totalProjectManagementEffort;
	}

	public void setTotalProjectManagementEffort(double totalProjectManagementEffort) {
		this.totalProjectManagementEffort = totalProjectManagementEffort;
	}

	public double getTotalBusinessAnalysisEffort() {
		return totalBusinessAnalysisEffort;
	}

	public void setTotalBusinessAnalysisEffort(double totalBusinessAnalysisEffort) {
		this.totalBusinessAnalysisEffort = totalBusinessAnalysisEffort;
	}

	public double getTotalDevelopmentEffort() {
		return totalDevelopmentEffort;
	}

	public void setTotalDevelopmentEffort(double totalDevelopmentEffort) {
		this.totalDevelopmentEffort = totalDevelopmentEffort;
	}

	public double getTotalQualityAssuranceEffort() {
		return totalQualityAssuranceEffort;
	}

	public void setTotalQualityAssuranceEffort(double totalQualityAssuranceEffort) {
		this.totalQualityAssuranceEffort = totalQualityAssuranceEffort;
	}

	public double getTotalAssetsAndAcceleratorsEffort() {
		return totalAssetsAndAcceleratorsEffort;
	}

	public void setTotalAssetsAndAcceleratorsEffort(double totalAssetsAndAcceleratorsEffort) {
		this.totalAssetsAndAcceleratorsEffort = totalAssetsAndAcceleratorsEffort;
	}

	public double getTotalGenAIEffort() {
		return totalGenAIEffort;
	}

	public void setTotalGenAIEffort(double totalGenAIEffort) {
		this.totalGenAIEffort = totalGenAIEffort;
	}

	public double getTotalPersonHours() {
		return totalPersonHours;
	}

	public void setTotalPersonHours(double totalPersonHours) {
		this.totalPersonHours = totalPersonHours;
	}

	public double getTotalPersonDays() {
		return totalPersonDays;
	}

	public void setTotalPersonDays(double totalPersonDays) {
		this.totalPersonDays = totalPersonDays;
	}

	public double getTotalPersonMonths() {
		return totalPersonMonths;
	}

	public void setTotalPersonMonths(double totalPersonMonths) {
		this.totalPersonMonths = totalPersonMonths;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<EstimationItemModel> getEstimationItems() {
		return estimationItems;
	}

	public void setEstimationItems(List<EstimationItemModel> estimationItems) {
		this.estimationItems = estimationItems;
	}

	public Long getTeaEstimateId() {
		return teaEstimateId;
	}

	public void setTeaEstimateId(Long teaEstimateId) {
		this.teaEstimateId = teaEstimateId;
	}

	public double getTotalThreePointEstimate() {
		return totalThreePointEstimate;
	}

	public void setTotalThreePointEstimate(double totalThreePointEstimate) {
		this.totalThreePointEstimate = totalThreePointEstimate;
	}
	
	public String toString() {
		return "EstimateDetailModel [estDetailId=" + estDetailId + ", engagementName=" + engagementName
				+ ", totalEffortInPersonHours=" + totalEffortInPersonHours + ", totalProjectManagementEffort="
				+ totalProjectManagementEffort + ", totalBusinessAnalysisEffort=" + totalBusinessAnalysisEffort
				+ ", totalDevelopmentEffort=" + totalDevelopmentEffort + ", totalQualityAssuranceEffort="
				+ totalQualityAssuranceEffort + ", totalAssetsAndAcceleratorsEffort="
				+ totalAssetsAndAcceleratorsEffort + ", totalGenAIEffort=" + totalGenAIEffort + ", totalPersonHours="
				+ totalPersonHours + ", totalPersonDays=" + totalPersonDays + ", totalPersonMonths="
				+ totalPersonMonths + ", version=" + version + ", totalThreePointEstimate=" + totalThreePointEstimate
				+ ", estimationItems=" + estimationItems + ", teaEstimateId=" + teaEstimateId + "]";
	}
}