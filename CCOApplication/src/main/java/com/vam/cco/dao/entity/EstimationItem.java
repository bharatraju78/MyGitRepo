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
@Table(name = "ESTIMATE_ITEM")
public class EstimationItem implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6038660190605392039L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineItemID;
	
	private String track;
	private String lineItem;
	private String classification;
	private String assumptionReference;
	private double optimisticPersonHours;
	private double pessimisticPersonHours;
	private double meanPersonHours;
	private double threePointEstimate;
	private double effortInPersonHours;
	private double projectManagementEffort;
	private double businessAnalysisEffort;
	private double developmentEffort;
	private double qualityAssuranceEffort;
	private double assetsAndAcceleratorsEffort;
	private double genAIEffort;
	private double personHours;
	private double personDays;
	private double personMonths;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EST_DETAIL_ID") // Foreign key column in the EstimateDetail table
	private EstimateDetail estimateDetail;
	
	public EstimationItem() {
		// TODO Auto-generated constructor stub
	}

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

	public EstimateDetail getEstimateDetail() {
		return estimateDetail;
	}

	public void setEstimateDetail(EstimateDetail estimateDetail) {
		this.estimateDetail = estimateDetail;
	}

	
}
