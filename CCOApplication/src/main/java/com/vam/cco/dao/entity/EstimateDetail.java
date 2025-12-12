package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ESTIMATE_DETAIL")
public class EstimateDetail implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8332675398550172663L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EST_DETAIL_ID")
    private Long estDetailId;
	
	private String engagementName;
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
	
	@OneToMany(mappedBy = "estimateDetail", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EstimationItem> estimationItems;
	
	@OneToOne(mappedBy = "estimateDetail") // Foreign key column in the TeaEstimate table
	private TeaEstimate teaEstimate;

	@javax.persistence.Version
    private Long version;

	public EstimateDetail() {
		this.estimationItems = new ArrayList<EstimationItem>();
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

	public List<EstimationItem> getEstimationItems() {
		return estimationItems;
	}

	public void setEstimationItems(List<EstimationItem> estimationItems) {
		this.estimationItems = estimationItems;
	}

	public TeaEstimate getTeaEstimate() {
		return teaEstimate;
	}

	public void setTeaEstimate(TeaEstimate teaEstimate) {
		this.teaEstimate = teaEstimate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
