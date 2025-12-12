package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TEA_ESTIMATE")
public class TeaEstimate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8627492941459802578L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String clientOrProspect;
	private String engagementName;
	private String businessObjective;
	private String engagementType;
	private String estimatedBy;
	private String verifiedBy;
	private String approvedBy;
	
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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "prj_start_date", nullable = false)
	private Date projectStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "estimation_date", nullable = true)
	private Date estimationDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "verification_date", nullable = true)
	private Date verificationDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "approval_date", nullable = true)
	private Date approvalDate;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "EST_DETAIL_ID", referencedColumnName = "EST_DETAIL_ID")
	private EstimateDetail estimateDetail;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "RES_DETAIL_ID", referencedColumnName = "RES_DETAIL_ID")
	private ResourceDetail resourceDetail;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "EFF_DETAIL_ID", referencedColumnName = "EFF_DETAIL_ID")
	private EffortDetail effortDetail;

	public TeaEstimate() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientOrProspect() {
		return clientOrProspect;
	}

	public void setClientOrProspect(String clientOrProspect) {
		this.clientOrProspect = clientOrProspect;
	}

	public String getEngagementName() {
		return engagementName;
	}

	public void setEngagementName(String engagementName) {
		this.engagementName = engagementName;
	}

	public String getBusinessObjective() {
		return businessObjective;
	}

	public void setBusinessObjective(String businessObjective) {
		this.businessObjective = businessObjective;
	}

	public String getEngagementType() {
		return engagementType;
	}

	public void setEngagementType(String engagementType) {
		this.engagementType = engagementType;
	}

	public String getEstimatedBy() {
		return estimatedBy;
	}

	public void setEstimatedBy(String estimatedBy) {
		this.estimatedBy = estimatedBy;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
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

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public EstimateDetail getEstimateDetail() {
		return estimateDetail;
	}

	public void setEstimateDetail(EstimateDetail estimateDetail) {
		this.estimateDetail = estimateDetail;
	}

	public ResourceDetail getResourceDetail() {
		return resourceDetail;
	}

	public void setResourceDetail(ResourceDetail resourceDetail) {
		this.resourceDetail = resourceDetail;
	}

	public EffortDetail getEffortDetail() { return effortDetail; }
	public void setEffortDetail(EffortDetail effortDetail) { this.effortDetail = effortDetail; }

	public Date getEstimationDate() {
		return estimationDate;
	}

	public void setEstimationDate(Date estimationDate) {
		this.estimationDate = estimationDate;
	}

	public Date getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

}
