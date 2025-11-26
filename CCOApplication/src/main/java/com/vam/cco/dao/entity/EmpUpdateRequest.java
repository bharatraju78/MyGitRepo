package com.vam.cco.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emp_update_request")
public class EmpUpdateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestById;
    private Long requestToId;
    private Long employeeId;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String comments;
    private String status;
    private String requestType;
    private String requestNumber;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Transient
    private String requestByName;
    
    @Transient
    private String requestToName;
    
    @Transient
    private String employeeName;
    
    @Transient
    private Long projectId;
    
    @Transient
    private String actionFrom;
    
    public EmpUpdateRequest() {
	}
    
    public EmpUpdateRequest(String requestNumber,Long id, Long requestById, Long requestToId, Long employeeId, Date startDate, Date endDate,
							String comments, String status, String requestByName, String requestToName, String employeeName, String requestType) {
		this.requestNumber = requestNumber;
    	this.id = id;
    	this.requestById = requestById;
		this.requestToId = requestToId;
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.comments = comments;
		this.status = status;
		this.requestByName = requestByName;
		this.requestToName = requestToName;
		this.employeeName = employeeName;
		this.requestType = requestType;
	}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestById() {
        return requestById;
    }

    public void setRequestById(Long requestById) {
        this.requestById = requestById;
    }

    public Long getRequestToId() {
        return requestToId;
    }

    public void setRequestToId(Long requestToId) {
        this.requestToId = requestToId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

	public String getRequestByName() {
		return requestByName;
	}

	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}

	public String getRequestToName() {
		return requestToName;
	}

	public void setRequestToName(String requestToName) {
		this.requestToName = requestToName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return "EmpUpdateRequest [id=" + id + ", requestById=" + requestById + ", requestToId=" + requestToId
				+ ", employeeId=" + employeeId + ", startDate=" + startDate + ", endDate=" + endDate + ", comments="
				+ comments + ", status=" + status + ", requestType=" + requestType + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", requestByName=" + requestByName + ", requestToName=" + requestToName + ", employeeName="
				+ employeeName + "]";
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getActionFrom() {
		return actionFrom;
	}

	public void setActionFrom(String actionFrom) {
		this.actionFrom = actionFrom;
	}
	
}