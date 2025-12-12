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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EFFORT_DETAIL")
public class EffortDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EFF_DETAIL_ID")
    private Long effDetailId;

    // switched to Effort-specific total entities
    @OneToOne(cascade = CascadeType.ALL)
    private EffortOnsiteTotal onsiteTotal;

    @OneToOne(cascade = CascadeType.ALL)
    private EffortOffshoreTotal offshoreTotal;

    @OneToMany(mappedBy = "effortDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EffortInfo> effortInfos;

    private double totalTeamSize;
    private double totalOnsiteTeamSize;
    private double totalOffshoreTeamSize;

    @OneToOne(mappedBy = "effortDetail")
    private TeaEstimate teaEstimate; // kept similar to ResourceDetail but note mapping may differ in future

    public EffortDetail() {
        this.effortInfos = new ArrayList<>();
        this.onsiteTotal = new EffortOnsiteTotal();
        this.offshoreTotal = new EffortOffshoreTotal();
    }

    public Long getEffDetailId() { return effDetailId; }
    public void setEffDetailId(Long effDetailId) { this.effDetailId = effDetailId; }

    public EffortOnsiteTotal getOnsiteTotal() { return onsiteTotal; }
    public void setOnsiteTotal(EffortOnsiteTotal onsiteTotal) { this.onsiteTotal = onsiteTotal; }

    public EffortOffshoreTotal getOffshoreTotal() { return offshoreTotal; }
    public void setOffshoreTotal(EffortOffshoreTotal offshoreTotal) { this.offshoreTotal = offshoreTotal; }

    public List<EffortInfo> getEffortInfos() { return effortInfos; }
    public void setEffortInfos(List<EffortInfo> effortInfos) { this.effortInfos = effortInfos; }

    public double getTotalTeamSize() { return totalTeamSize; }
    public void setTotalTeamSize(double totalTeamSize) { this.totalTeamSize = totalTeamSize; }

    public double getTotalOnsiteTeamSize() { return totalOnsiteTeamSize; }
    public void setTotalOnsiteTeamSize(double totalOnsiteTeamSize) { this.totalOnsiteTeamSize = totalOnsiteTeamSize; }

    public double getTotalOffshoreTeamSize() { return totalOffshoreTeamSize; }
    public void setTotalOffshoreTeamSize(double totalOffshoreTeamSize) { this.totalOffshoreTeamSize = totalOffshoreTeamSize; }

    public TeaEstimate getTeaEstimate() { return teaEstimate; }
    public void setTeaEstimate(TeaEstimate teaEstimate) { this.teaEstimate = teaEstimate; }

}