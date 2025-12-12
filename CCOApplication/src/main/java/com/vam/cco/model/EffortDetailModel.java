package com.vam.cco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class EffortDetailModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long effDetailId;
    private EffortOnsiteTotalModel onsiteTotal;
    private EffortOffshoreTotalModel offshoreTotal;
    @Valid
    private List<EffortInfoModel> effortInfos;
    private double totalTeamSize;
    private double totalOnsiteTeamSize;
    private double totalOffshoreTeamSize;
    private Long teaEstimateId;

    public EffortDetailModel() {
        this.effortInfos = new ArrayList<>();
        this.onsiteTotal = new EffortOnsiteTotalModel();
        this.offshoreTotal = new EffortOffshoreTotalModel();
    }

    public Long getEffDetailId() { return effDetailId; }
    public void setEffDetailId(Long effDetailId) { this.effDetailId = effDetailId; }

    public EffortOnsiteTotalModel getOnsiteTotal() { return onsiteTotal; }
    public void setOnsiteTotal(EffortOnsiteTotalModel onsiteTotal) { this.onsiteTotal = onsiteTotal; }

    public EffortOffshoreTotalModel getOffshoreTotal() { return offshoreTotal; }
    public void setOffshoreTotal(EffortOffshoreTotalModel offshoreTotal) { this.offshoreTotal = offshoreTotal; }

    public List<EffortInfoModel> getEffortInfos() { return effortInfos; }
    public void setEffortInfos(List<EffortInfoModel> effortInfos) { this.effortInfos = effortInfos; }

    public double getTotalTeamSize() { return totalTeamSize; }
    public void setTotalTeamSize(double totalTeamSize) { this.totalTeamSize = totalTeamSize; }

    public double getTotalOnsiteTeamSize() { return totalOnsiteTeamSize; }
    public void setTotalOnsiteTeamSize(double totalOnsiteTeamSize) { this.totalOnsiteTeamSize = totalOnsiteTeamSize; }

    public double getTotalOffshoreTeamSize() { return totalOffshoreTeamSize; }
    public void setTotalOffshoreTeamSize(double totalOffshoreTeamSize) { this.totalOffshoreTeamSize = totalOffshoreTeamSize; }

    public Long getTeaEstimateId() { return teaEstimateId; }
    public void setTeaEstimateId(Long teaEstimateId) { this.teaEstimateId = teaEstimateId; }
}