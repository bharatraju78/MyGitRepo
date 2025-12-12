package com.vam.cco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.vam.cco.validation.NotPastDate;

public class TeaEstimateModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "Client or Prospect is required")
	private String clientOrProspect;

	@NotBlank(message = "Engagement Name is required")
	@Size(max = 200, message = "Engagement Name must be at most 200 characters")
	private String engagementName;
	@Size(max = 500, message = "Business objective must be at most 500 characters")
	private String businessObjective;
	@Pattern(regexp = "[A-Za-z\\- ]{0,50}", message = "Engagement Type can contain letters, spaces and hyphens, max 50 chars")
	private String engagementType;
	private String estimatedBy;
	private String verifiedBy;
	private String approvedBy;

	private String createdBy;
	private String modifiedBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modifiedDate;

	@NotPastDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date projectStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date estimationDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date verificationDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approvalDate;

	@Valid
	private EstimateDetailModel estimateDetail;
	@Valid
	private ResourceDetailModel resourceDetail;
	@Valid
	private EffortDetailModel effortDetail;
	
	private List<String> classificationList;

	public TeaEstimateModel() {
		this.estimateDetail = new EstimateDetailModel();
		this.resourceDetail = new ResourceDetailModel();
		this.effortDetail = new EffortDetailModel();
		classificationList = new ArrayList<String>();
		
	}

	// Projection constructor used by repository JPQL "new" expression
	public TeaEstimateModel(Long id, String clientOrProspect, String engagementName, String businessObjective,
			String engagementType, String estimatedBy, String verifiedBy, String approvedBy, String createdBy,
			String modifiedBy, Date createdDate, Date modifiedDate, Date projectStartDate, Date estimationDate,
			Date verificationDate, Date approvalDate) {
		this.id = id;
		this.clientOrProspect = clientOrProspect;
		this.engagementName = engagementName;
		this.businessObjective = businessObjective;
		this.engagementType = engagementType;
		this.estimatedBy = estimatedBy;
		this.verifiedBy = verifiedBy;
		this.approvedBy = approvedBy;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.projectStartDate = projectStartDate;
		this.estimationDate = estimationDate;
		this.verificationDate = verificationDate;
		this.approvalDate = approvalDate;
		this.estimateDetail = new EstimateDetailModel();
		this.resourceDetail = new ResourceDetailModel();
		this.effortDetail = new EffortDetailModel();
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

	public EstimateDetailModel getEstimateDetail() {
		return estimateDetail;
	}

	public void setEstimateDetail(EstimateDetailModel estimateDetail) {
		this.estimateDetail = estimateDetail;
	}

	public ResourceDetailModel getResourceDetail() {
		return resourceDetail;
	}

	public void setResourceDetail(ResourceDetailModel resourceDetail) {
		this.resourceDetail = resourceDetail;
	}

	public EffortDetailModel getEffortDetail() {
		return effortDetail;
	}

	public void setEffortDetail(EffortDetailModel effortDetail) {
		this.effortDetail = effortDetail;
	}

	public List<String> getClassificationList() {
		return classificationList;
	}

	public void setClassificationList(List<String> classificationList) {
		this.classificationList = classificationList;
	}

}