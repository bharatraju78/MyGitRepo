package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.vam.cco.model.AccountModel;

@Entity
@Table(name = "portfolio")
public class Portfolio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "portfolio_number", unique = true, nullable = false)
	private String portfolioNmber;

	@Column(nullable = false)
	private String name;

	@Column(name = "portfolio_leader")
	private Long portfolioLeader;

	@Column(name = "portfolio_hr")
	private Long portfolioHR;
	
	@Column(name = "portfolio_hr_1")
	private Long portfolioHR1;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "created_at")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	private Boolean active;

	@Transient
	private String leaderName;

	@Transient
	private String hrName;

	@Transient
	private Long accountId;

	@Transient
	private Long portfolioId;

	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date startDate;

	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date endDate;

	@Transient
	private List<AccountModel> accountsModel;

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts;

	public Portfolio() {
	}

	public Portfolio(Long id, String portfolioNmber, String name, Boolean active, Long portfolioLeader,
			String leaderName, Long portfolioHR, String hrName, String description) {
		this.id = id;
		this.name = name;
		this.portfolioNmber = portfolioNmber;
		this.active = active;
		this.portfolioLeader = portfolioLeader;
		this.leaderName = leaderName;
		this.portfolioHR = portfolioHR;
		this.hrName = hrName;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPortfolioNmber() {
		return portfolioNmber;
	}

	public void setPortfolioNmber(String portfolioNmber) {
		this.portfolioNmber = portfolioNmber;
	}

	public Long getPortfolioLeader() {
		return portfolioLeader;
	}

	public void setPortfolioLeader(Long portfolioLeader) {
		this.portfolioLeader = portfolioLeader;
	}

	public Long getPortfolioHR() {
		return portfolioHR;
	}

	public void setPortfolioHR(Long portfolioHR) {
		this.portfolioHR = portfolioHR;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Portfolio [id=" + id + ", portfolioNmber=" + portfolioNmber + ", name=" + name + ", portfolioLeader="
				+ portfolioLeader + ", portfolioHR=" + portfolioHR + ", description=" + description + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", active=" + active + ", leaderName=" + leaderName + ", hrName=" + hrName + "]";
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getHrName() {
		return hrName;
	}

	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Long portfolioId) {
		this.portfolioId = portfolioId;
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

	public List<AccountModel> getAccountsModel() {
		return accountsModel;
	}

	public void setAccountsModel(List<AccountModel> accountsModel) {
		this.accountsModel = accountsModel;
	}

	public Long getPortfolioHR1() {
		return portfolioHR1;
	}

	public void setPortfolioHR1(Long portfolioHR1) {
		this.portfolioHR1 = portfolioHR1;
	}

}