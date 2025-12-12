package com.vam.cco.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESOURCE_DETAIL")
public class ResourceDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7249969230571033869L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RES_DETAIL_ID")
    private Long resDetailId;
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ONSITE_TOTAL_ID", referencedColumnName = "id")
	private OnsiteTotal onsiteTotal;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OFFSHORE_TOTAL_ID", referencedColumnName = "id")
	private OffshoreTotal offshoreTotal;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHIFT_ALLOWANCE_ID", referencedColumnName = "id")
	private ShiftAllowanceCost shiftAllowanceCost;
	
	@OneToMany(mappedBy = "resourceDetail", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ResourceInfo> resourceInfos;
	
	private double totalTeamSize;
	private double totalOnsiteTeamSize;
	private double totalOffshoreTeamSize;
	
	@OneToOne(mappedBy = "resourceDetail")
	private TeaEstimate teaEstimate;
	
	
	
	
	public ResourceDetail() {
		this.resourceInfos = new ArrayList<ResourceInfo>();
		this.onsiteTotal = new OnsiteTotal();
		this.offshoreTotal = new OffshoreTotal();
		this.shiftAllowanceCost = new ShiftAllowanceCost();
	}

	public Long getResDetailId() {
		return resDetailId;
	}

	public void setResDetailId(Long resDetailId) {
		this.resDetailId = resDetailId;
	}

	public OnsiteTotal getOnsiteTotal() {
		return onsiteTotal;
	}

	public void setOnsiteTotal(OnsiteTotal onsiteTotal) {
		this.onsiteTotal = onsiteTotal;
	}

	public OffshoreTotal getOffshoreTotal() {
		return offshoreTotal;
	}

	public void setOffshoreTotal(OffshoreTotal offshoreTotal) {
		this.offshoreTotal = offshoreTotal;
	}

	public ShiftAllowanceCost getShiftAllowanceCost() {
		return shiftAllowanceCost;
	}

	public void setShiftAllowanceCost(ShiftAllowanceCost shiftAllowanceCost) {
		this.shiftAllowanceCost = shiftAllowanceCost;
	}

	public List<ResourceInfo> getResourceInfos() {
		return resourceInfos;
	}

	public void setResourceInfos(List<ResourceInfo> resourceInfos) {
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

	public TeaEstimate getTeaEstimate() {
		return teaEstimate;
	}

	public void setTeaEstimate(TeaEstimate teaEstimate) {
		this.teaEstimate = teaEstimate;
	}
	
}
