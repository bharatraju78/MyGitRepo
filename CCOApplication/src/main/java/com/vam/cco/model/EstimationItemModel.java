package com.vam.cco.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class EstimationItemModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long lineItemID;
	private String track;
	@NotBlank(message = "Line item is required")
	private String lineItem;
	private String classification;
	private String assumptionReference;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double optimisticPersonHours;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double pessimisticPersonHours;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double meanPersonHours;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double threePointEstimate;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double effortInPersonHours;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double projectManagementEffort;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double businessAnalysisEffort;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double developmentEffort;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double qualityAssuranceEffort;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double assetsAndAcceleratorsEffort;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double genAIEffort;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double personHours;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double personDays;
	@PositiveOrZero(message = "Hours must be zero or positive")
	private double personMonths;

	private Long estimateDetailId; // parent reference

	public EstimationItemModel() {}

	public Long getLineItemID() {
		return lineItemID;
	}

	public void setLineItemID(Long lineItemID) {
		this.lineItemID = lineItemID;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getLineItem() {
		return lineItem;
	}

	public void setLineItem(String lineItem) {
		this.lineItem = lineItem;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getAssumptionReference() {
		return assumptionReference;
	}

	public void setAssumptionReference(String assumptionReference) {
		this.assumptionReference = assumptionReference;
	}

	public double getOptimisticPersonHours() {
		return optimisticPersonHours;
	}

	public void setOptimisticPersonHours(double optimisticPersonHours) {
		this.optimisticPersonHours = optimisticPersonHours;
	}

	public double getPessimisticPersonHours() {
		return pessimisticPersonHours;
	}

	public void setPessimisticPersonHours(double pessimisticPersonHours) {
		this.pessimisticPersonHours = pessimisticPersonHours;
	}

	public double getMeanPersonHours() {
		return meanPersonHours;
	}

	public void setMeanPersonHours(double meanPersonHours) {
		this.meanPersonHours = meanPersonHours;
	}

	public double getThreePointEstimate() {
		return threePointEstimate;
	}

	public void setThreePointEstimate(double threePointEstimate) {
		this.threePointEstimate = threePointEstimate;
	}

	public double getEffortInPersonHours() {
		return effortInPersonHours;
	}

	public void setEffortInPersonHours(double effortInPersonHours) {
		this.effortInPersonHours = effortInPersonHours;
	}

	public double getProjectManagementEffort() {
		return projectManagementEffort;
	}

	public void setProjectManagementEffort(double projectManagementEffort) {
		this.projectManagementEffort = projectManagementEffort;
	}

	public double getBusinessAnalysisEffort() {
		return businessAnalysisEffort;
	}

	public void setBusinessAnalysisEffort(double businessAnalysisEffort) {
		this.businessAnalysisEffort = businessAnalysisEffort;
	}

	public double getDevelopmentEffort() {
		return developmentEffort;
	}

	public void setDevelopmentEffort(double developmentEffort) {
		this.developmentEffort = developmentEffort;
	}

	public double getQualityAssuranceEffort() {
		return qualityAssuranceEffort;
	}

	public void setQualityAssuranceEffort(double qualityAssuranceEffort) {
		this.qualityAssuranceEffort = qualityAssuranceEffort;
	}

	public double getAssetsAndAcceleratorsEffort() {
		return assetsAndAcceleratorsEffort;
	}

	public void setAssetsAndAcceleratorsEffort(double assetsAndAcceleratorsEffort) {
		this.assetsAndAcceleratorsEffort = assetsAndAcceleratorsEffort;
	}

	public double getGenAIEffort() {
		return genAIEffort;
	}

	public void setGenAIEffort(double genAIEffort) {
		this.genAIEffort = genAIEffort;
	}

	public double getPersonHours() {
		return personHours;
	}

	public void setPersonHours(double personHours) {
		this.personHours = personHours;
	}

	public double getPersonDays() {
		return personDays;
	}

	public void setPersonDays(double personDays) {
		this.personDays = personDays;
	}

	public double getPersonMonths() {
		return personMonths;
	}

	public void setPersonMonths(double personMonths) {
		this.personMonths = personMonths;
	}

	public Long getEstimateDetailId() {
		return estimateDetailId;
	}

	public void setEstimateDetailId(Long estimateDetailId) {
		this.estimateDetailId = estimateDetailId;
	}

	public String toString() {
		return "EstimationItemModel [lineItemID=" + lineItemID + ", track=" + track + ", lineItem=" + lineItem
				+ ", classification=" + classification + ", assumptionReference=" + assumptionReference
				+ ", optimisticPersonHours=" + optimisticPersonHours + ", pessimisticPersonHours="
				+ pessimisticPersonHours + ", meanPersonHours=" + meanPersonHours + ", threePointEstimate="
				+ threePointEstimate + ", effortInPersonHours=" + effortInPersonHours + ", projectManagementEffort="
				+ projectManagementEffort + ", businessAnalysisEffort=" + businessAnalysisEffort
				+ ", developmentEffort=" + developmentEffort + ", qualityAssuranceEffort=" + qualityAssuranceEffort
				+ ", assetsAndAcceleratorsEffort=" + assetsAndAcceleratorsEffort + ", genAIEffort=" + genAIEffort
				+ ", personHours=" + personHours + ", personDays=" + personDays + ", personMonths=" + personMonths
				+ ", estimateDetailId=" + estimateDetailId + "]";
	}
}