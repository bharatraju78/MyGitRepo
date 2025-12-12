package com.vam.cco.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vam.cco.dao.entity.EstimateDetail;
import com.vam.cco.dao.entity.EstimationItem;
import com.vam.cco.dao.entity.ResourceDetail;
import com.vam.cco.dao.entity.ResourceInfo;
import com.vam.cco.dao.entity.TeaEstimate;
import com.vam.cco.model.EstimateDetailModel;
import com.vam.cco.model.EstimationItemModel;
import com.vam.cco.model.ResourceDetailModel;
import com.vam.cco.model.ResourceInfoModel;
import com.vam.cco.model.TeaEstimateModel;
import com.vam.cco.dao.entity.EffortDetail;
import com.vam.cco.dao.entity.EffortInfo;
import com.vam.cco.model.EffortDetailModel;
import com.vam.cco.model.EffortInfoModel;
import com.vam.cco.model.EffortOnsiteTotalModel;
import com.vam.cco.model.EffortOffshoreTotalModel;

public class TeaEstimateMapper {

    public static TeaEstimate toEntity(TeaEstimateModel model) {
        if (model == null) return null;
        TeaEstimate entity = new TeaEstimate();
        entity.setId(model.getId());
        entity.setClientOrProspect(model.getClientOrProspect());
        entity.setEngagementName(model.getEngagementName());
        entity.setBusinessObjective(model.getBusinessObjective());
        entity.setEngagementType(model.getEngagementType());
        entity.setEstimatedBy(model.getEstimatedBy());
        entity.setVerifiedBy(model.getVerifiedBy());
        entity.setApprovedBy(model.getApprovedBy());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setModifiedBy(model.getModifiedBy());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setModifiedDate(model.getModifiedDate());
        entity.setProjectStartDate(model.getProjectStartDate());
        entity.setEstimationDate(model.getEstimationDate());
        entity.setVerificationDate(model.getVerificationDate());
        entity.setApprovalDate(model.getApprovalDate());

        // map single estimateDetail
        if (model.getEstimateDetail() != null) {
            EstimateDetailModel edm = model.getEstimateDetail();
            EstimateDetail ed = new EstimateDetail();
            ed.setEstDetailId(edm.getEstDetailId());
            ed.setEngagementName(edm.getEngagementName());
            ed.setVersion(edm.getVersion());
            ed.setTotalEffortInPersonHours(edm.getTotalEffortInPersonHours());
            ed.setTotalProjectManagementEffort(edm.getTotalProjectManagementEffort());
            ed.setTotalBusinessAnalysisEffort(edm.getTotalBusinessAnalysisEffort());
            ed.setTotalDevelopmentEffort(edm.getTotalDevelopmentEffort());
            ed.setTotalQualityAssuranceEffort(edm.getTotalQualityAssuranceEffort());
            ed.setTotalAssetsAndAcceleratorsEffort(edm.getTotalAssetsAndAcceleratorsEffort());
            ed.setTotalGenAIEffort(edm.getTotalGenAIEffort());
            ed.setTotalPersonHours(edm.getTotalPersonHours());
            ed.setTotalPersonDays(edm.getTotalPersonDays());
            ed.setTotalPersonMonths(edm.getTotalPersonMonths());
            if (edm.getEstimationItems() != null) {
                List<EstimationItem> items = edm.getEstimationItems().stream().map(i -> {
                    EstimationItem ei = new EstimationItem();
                    ei.setLineItemID(i.getLineItemID());
                    ei.setTrack(i.getTrack());
                    ei.setLineItem(i.getLineItem());
                    ei.setClassification(i.getClassification());
                    ei.setAssumptionReference(i.getAssumptionReference());
                    ei.setOptimisticPersonHours(i.getOptimisticPersonHours());
                    ei.setPessimisticPersonHours(i.getPessimisticPersonHours());
                    ei.setMeanPersonHours(i.getMeanPersonHours());
                    ei.setThreePointEstimate(i.getThreePointEstimate());
                    ei.setEffortInPersonHours(i.getEffortInPersonHours());
                    ei.setProjectManagementEffort(i.getProjectManagementEffort());
                    ei.setBusinessAnalysisEffort(i.getBusinessAnalysisEffort());
                    ei.setDevelopmentEffort(i.getDevelopmentEffort());
                    ei.setQualityAssuranceEffort(i.getQualityAssuranceEffort());
                    ei.setAssetsAndAcceleratorsEffort(i.getAssetsAndAcceleratorsEffort());
                    ei.setGenAIEffort(i.getGenAIEffort());
                    ei.setPersonHours(i.getPersonHours());
                    ei.setPersonDays(i.getPersonDays());
                    ei.setPersonMonths(i.getPersonMonths());
                    ei.setEstimateDetail(ed);
                    return ei;
                }).collect(Collectors.toList());
                ed.setEstimationItems(items);
            }
            ed.setTeaEstimate(entity);
            entity.setEstimateDetail(ed);
        }

        // map single resourceDetail
        if (model.getResourceDetail() != null) {
            ResourceDetailModel rdm = model.getResourceDetail();
            ResourceDetail rd = new ResourceDetail();
            rd.setResDetailId(rdm.getResDetailId());
            rd.setTotalTeamSize(rdm.getTotalTeamSize());
            rd.setTotalOnsiteTeamSize(rdm.getTotalOnsiteTeamSize());
            rd.setTotalOffshoreTeamSize(rdm.getTotalOffshoreTeamSize());
            if (rdm.getResourceInfos() != null) {
                List<ResourceInfo> infos = rdm.getResourceInfos().stream().map(ri -> {
                    ResourceInfo info = new ResourceInfo();
                    info.setInfoId(ri.getInfoId());
                    info.setTrack(ri.getTrack());
                    info.setLocation(ri.getLocation());
                    info.setRole(ri.getRole());
                    info.setRoleType(ri.getRoleType());
                    info.setShift(ri.getShift());
                    info.setEmployeeType(ri.getEmployeeType());
                    info.setVmBand(ri.getVmBand());
                    info.setRate(ri.getRate());
                    info.setHoursPerMonth(ri.getHoursPerMonth());
                    info.setYearOneJan(ri.getYearOneJan());
                    info.setYearOneFeb(ri.getYearOneFeb());
                    info.setYearOneMar(ri.getYearOneMar());
                    info.setYearOneApr(ri.getYearOneApr());
                    info.setYearOneMay(ri.getYearOneMay());
                    info.setYearOneJun(ri.getYearOneJun());
                    info.setYearOneJul(ri.getYearOneJul());
                    info.setYearOneAug(ri.getYearOneAug());
                    info.setYearOneSep(ri.getYearOneSep());
                    info.setYearOneOct(ri.getYearOneOct());
                    info.setYearOneNov(ri.getYearOneNov());
                    info.setYearOneDec(ri.getYearOneDec());
                    info.setYearOneTotal(ri.getYearOneTotal());
                    // ...other year fields omitted for brevity but could be mapped similarly
                    info.setResourceDetail(rd);
                    return info;
                }).collect(Collectors.toList());
                rd.setResourceInfos(infos);
            }
            rd.setTeaEstimate(entity);
            entity.setResourceDetail(rd);
        }

        // map single effortDetail (new)
        if (model.getEffortDetail() != null) {
            EffortDetailModel edm = model.getEffortDetail();
            EffortDetail ed = new EffortDetail();
            ed.setEffDetailId(edm.getEffDetailId());
            ed.setTotalTeamSize(edm.getTotalTeamSize());
            ed.setTotalOnsiteTeamSize(edm.getTotalOnsiteTeamSize());
            ed.setTotalOffshoreTeamSize(edm.getTotalOffshoreTeamSize());
            if (edm.getEffortInfos() != null) {
                List<EffortInfo> infos = edm.getEffortInfos().stream().map(ri -> {
                    EffortInfo info = new EffortInfo();
                    info.setInfoId(ri.getInfoId());
                    info.setTrack(ri.getTrack());
                    info.setLocation(ri.getLocation());
                    info.setRole(ri.getRole());
                    info.setRoleType(ri.getRoleType());
                    info.setShift(ri.getShift());
                    info.setEmployeeType(ri.getEmployeeType());
                    info.setVmBand(ri.getVmBand());
                    info.setRate(ri.getRate());
                    info.setHoursPerMonth(ri.getHoursPerMonth());
                    info.setYearOneJan(ri.getYearOneJan());
                    info.setYearOneFeb(ri.getYearOneFeb());
                    info.setYearOneMar(ri.getYearOneMar());
                    info.setYearOneApr(ri.getYearOneApr());
                    info.setYearOneMay(ri.getYearOneMay());
                    info.setYearOneJun(ri.getYearOneJun());
                    info.setYearOneJul(ri.getYearOneJul());
                    info.setYearOneAug(ri.getYearOneAug());
                    info.setYearOneSep(ri.getYearOneSep());
                    info.setYearOneOct(ri.getYearOneOct());
                    info.setYearOneNov(ri.getYearOneNov());
                    info.setYearOneDec(ri.getYearOneDec());
                    info.setYearOneTotal(ri.getYearOneTotal());
                    // ...other year fields omitted for brevity but could be mapped similarly
                    info.setEffortDetail(ed);
                    return info;
                }).collect(Collectors.toList());
                ed.setEffortInfos(infos);
            }
            ed.setTeaEstimate(entity);
            entity.setEffortDetail(ed);
        }

        return entity;
    }

    public static TeaEstimateModel toModel(TeaEstimate entity) {
        if (entity == null) return null;
        TeaEstimateModel model = new TeaEstimateModel();
        model.setId(entity.getId());
        model.setClientOrProspect(entity.getClientOrProspect());
        model.setEngagementName(entity.getEngagementName());
        model.setBusinessObjective(entity.getBusinessObjective());
        model.setEngagementType(entity.getEngagementType());
        model.setEstimatedBy(entity.getEstimatedBy());
        model.setVerifiedBy(entity.getVerifiedBy());
        model.setApprovedBy(entity.getApprovedBy());
        model.setCreatedBy(entity.getCreatedBy());
        model.setModifiedBy(entity.getModifiedBy());
        model.setCreatedDate(entity.getCreatedDate());
        model.setModifiedDate(entity.getModifiedDate());
        model.setProjectStartDate(entity.getProjectStartDate());
        model.setEstimationDate(entity.getEstimationDate());
        model.setVerificationDate(entity.getVerificationDate());
        model.setApprovalDate(entity.getApprovalDate());

        if (entity.getEstimateDetail() != null) {
            EstimateDetail ed = entity.getEstimateDetail();
            EstimateDetailModel edm = new EstimateDetailModel();
            edm.setEstDetailId(ed.getEstDetailId());
            edm.setEngagementName(ed.getEngagementName());
            edm.setVersion(ed.getVersion());
            edm.setTotalEffortInPersonHours(ed.getTotalEffortInPersonHours());
            edm.setTotalProjectManagementEffort(ed.getTotalProjectManagementEffort());
            edm.setTotalBusinessAnalysisEffort(ed.getTotalBusinessAnalysisEffort());
            edm.setTotalDevelopmentEffort(ed.getTotalDevelopmentEffort());
            edm.setTotalQualityAssuranceEffort(ed.getTotalQualityAssuranceEffort());
            edm.setTotalAssetsAndAcceleratorsEffort(ed.getTotalAssetsAndAcceleratorsEffort());
            edm.setTotalGenAIEffort(ed.getTotalGenAIEffort());
            edm.setTotalPersonHours(ed.getTotalPersonHours());
            edm.setTotalPersonDays(ed.getTotalPersonDays());
            edm.setTotalPersonMonths(ed.getTotalPersonMonths());
            if (ed.getEstimationItems() != null) {
                List<EstimationItemModel> items = ed.getEstimationItems().stream().map(i -> {
                    EstimationItemModel im = new EstimationItemModel();
                    im.setLineItemID(i.getLineItemID());
                    im.setTrack(i.getTrack());
                    im.setLineItem(i.getLineItem());
                    im.setClassification(i.getClassification());
                    im.setAssumptionReference(i.getAssumptionReference());
                    im.setOptimisticPersonHours(i.getOptimisticPersonHours());
                    im.setPessimisticPersonHours(i.getPessimisticPersonHours());
                    im.setMeanPersonHours(i.getMeanPersonHours());
                    im.setThreePointEstimate(i.getThreePointEstimate());
                    im.setEffortInPersonHours(i.getEffortInPersonHours());
                    im.setProjectManagementEffort(i.getProjectManagementEffort());
                    im.setBusinessAnalysisEffort(i.getBusinessAnalysisEffort());
                    im.setDevelopmentEffort(i.getDevelopmentEffort());
                    im.setQualityAssuranceEffort(i.getQualityAssuranceEffort());
                    im.setAssetsAndAcceleratorsEffort(i.getAssetsAndAcceleratorsEffort());
                    im.setGenAIEffort(i.getGenAIEffort());
                    im.setPersonHours(i.getPersonHours());
                    im.setPersonDays(i.getPersonDays());
                    im.setPersonMonths(i.getPersonMonths());
                    im.setEstimateDetailId(ed.getEstDetailId());
                    return im;
                }).collect(Collectors.toList());
                edm.setEstimationItems(items);
            }
            model.setEstimateDetail(edm);
            // set parent reference id for the detail model
            edm.setTeaEstimateId(entity.getId());
        }

        if (entity.getResourceDetail() != null) {
            ResourceDetail rd = entity.getResourceDetail();
            ResourceDetailModel rdm = new ResourceDetailModel();
            rdm.setResDetailId(rd.getResDetailId());
            rdm.setTotalTeamSize(rd.getTotalTeamSize());
            rdm.setTotalOnsiteTeamSize(rd.getTotalOnsiteTeamSize());
            rdm.setTotalOffshoreTeamSize(rd.getTotalOffshoreTeamSize());
            if (rd.getResourceInfos() != null) {
                List<ResourceInfoModel> rims = rd.getResourceInfos().stream().map(i -> {
                    ResourceInfoModel rim = new ResourceInfoModel();
                    rim.setInfoId(i.getInfoId());
                    rim.setTrack(i.getTrack());
                    rim.setLocation(i.getLocation());
                    rim.setRole(i.getRole());
                    rim.setRoleType(i.getRoleType());
                    rim.setShift(i.getShift());
                    rim.setEmployeeType(i.getEmployeeType());
                    rim.setVmBand(i.getVmBand());
                    rim.setRate(i.getRate());
                    rim.setHoursPerMonth(i.getHoursPerMonth());
                    rim.setYearOneJan(i.getYearOneJan());
                    rim.setYearOneFeb(i.getYearOneFeb());
                    rim.setYearOneMar(i.getYearOneMar());
                    rim.setYearOneApr(i.getYearOneApr());
                    rim.setYearOneMay(i.getYearOneMay());
                    rim.setYearOneJun(i.getYearOneJun());
                    rim.setYearOneJul(i.getYearOneJul());
                    rim.setYearOneAug(i.getYearOneAug());
                    rim.setYearOneSep(i.getYearOneSep());
                    rim.setYearOneOct(i.getYearOneOct());
                    rim.setYearOneNov(i.getYearOneNov());
                    rim.setYearOneDec(i.getYearOneDec());
                    rim.setYearOneTotal(i.getYearOneTotal());
                    // ...other year fields omitted for brevity
                    rim.setResourceDetailId(rd.getResDetailId());
                    return rim;
                }).collect(Collectors.toList());
                rdm.setResourceInfos(rims);
            }
            // set parent reference id for the resource detail model
            rdm.setTeaEstimateId(entity.getId());
            model.setResourceDetail(rdm);
        }

        // map effort detail entity to model
        if (entity.getEffortDetail() != null) {
            EffortDetail ed = entity.getEffortDetail();
            EffortDetailModel edm = new EffortDetailModel();
            edm.setEffDetailId(ed.getEffDetailId());
            edm.setTotalTeamSize(ed.getTotalTeamSize());
            edm.setTotalOnsiteTeamSize(ed.getTotalOnsiteTeamSize());
            edm.setTotalOffshoreTeamSize(ed.getTotalOffshoreTeamSize());
            if (ed.getEffortInfos() != null) {
                List<EffortInfoModel> eims = ed.getEffortInfos().stream().map(i -> {
                    EffortInfoModel im = new EffortInfoModel();
                    im.setInfoId(i.getInfoId());
                    im.setTrack(i.getTrack());
                    im.setLocation(i.getLocation());
                    im.setRole(i.getRole());
                    im.setRoleType(i.getRoleType());
                    im.setShift(i.getShift());
                    im.setEmployeeType(i.getEmployeeType());
                    im.setVmBand(i.getVmBand());
                    im.setRate(i.getRate());
                    im.setHoursPerMonth(i.getHoursPerMonth());
                    im.setYearOneJan(i.getYearOneJan());
                    im.setYearOneFeb(i.getYearOneFeb());
                    im.setYearOneMar(i.getYearOneMar());
                    im.setYearOneApr(i.getYearOneApr());
                    im.setYearOneMay(i.getYearOneMay());
                    im.setYearOneJun(i.getYearOneJun());
                    im.setYearOneJul(i.getYearOneJul());
                    im.setYearOneAug(i.getYearOneAug());
                    im.setYearOneSep(i.getYearOneSep());
                    im.setYearOneOct(i.getYearOneOct());
                    im.setYearOneNov(i.getYearOneNov());
                    im.setYearOneDec(i.getYearOneDec());
                    im.setYearOneTotal(i.getYearOneTotal());
                    // ...other year fields omitted for brevity
                    im.setEffortDetailId(ed.getEffDetailId());
                    return im;
                }).collect(Collectors.toList());
                edm.setEffortInfos(eims);
            }
            edm.setTeaEstimateId(entity.getId());
            model.setEffortDetail(edm);
        }

        return model;
    }

    // New: map EstimateDetail entity to EstimateDetailModel
    public static com.vam.cco.model.EstimateDetailModel estimateDetailToModel(EstimateDetail ed) {
        if (ed == null) return null;
        com.vam.cco.model.EstimateDetailModel edm = new com.vam.cco.model.EstimateDetailModel();
        edm.setEstDetailId(ed.getEstDetailId());
        edm.setEngagementName(ed.getEngagementName());
        edm.setVersion(ed.getVersion());
        edm.setTotalEffortInPersonHours(ed.getTotalEffortInPersonHours());
        edm.setTotalProjectManagementEffort(ed.getTotalProjectManagementEffort());
        edm.setTotalBusinessAnalysisEffort(ed.getTotalBusinessAnalysisEffort());
        edm.setTotalDevelopmentEffort(ed.getTotalDevelopmentEffort());
        edm.setTotalQualityAssuranceEffort(ed.getTotalQualityAssuranceEffort());
        edm.setTotalAssetsAndAcceleratorsEffort(ed.getTotalAssetsAndAcceleratorsEffort());
        edm.setTotalGenAIEffort(ed.getTotalGenAIEffort());
        edm.setTotalPersonHours(ed.getTotalPersonHours());
        edm.setTotalPersonDays(ed.getTotalPersonDays());
        edm.setTotalPersonMonths(ed.getTotalPersonMonths());
        if (ed.getEstimationItems() != null) {
            java.util.List<com.vam.cco.model.EstimationItemModel> items = ed.getEstimationItems().stream().map(i -> {
                com.vam.cco.model.EstimationItemModel im = new com.vam.cco.model.EstimationItemModel();
                im.setLineItemID(i.getLineItemID());
                im.setTrack(i.getTrack());
                im.setLineItem(i.getLineItem());
                im.setClassification(i.getClassification());
                im.setAssumptionReference(i.getAssumptionReference());
                im.setOptimisticPersonHours(i.getOptimisticPersonHours());
                im.setPessimisticPersonHours(i.getPessimisticPersonHours());
                im.setMeanPersonHours(i.getMeanPersonHours());
                im.setThreePointEstimate(i.getThreePointEstimate());
                im.setEffortInPersonHours(i.getEffortInPersonHours());
                im.setProjectManagementEffort(i.getProjectManagementEffort());
                im.setBusinessAnalysisEffort(i.getBusinessAnalysisEffort());
                im.setDevelopmentEffort(i.getDevelopmentEffort());
                im.setQualityAssuranceEffort(i.getQualityAssuranceEffort());
                im.setAssetsAndAcceleratorsEffort(i.getAssetsAndAcceleratorsEffort());
                im.setGenAIEffort(i.getGenAIEffort());
                im.setPersonHours(i.getPersonHours());
                im.setPersonDays(i.getPersonDays());
                im.setPersonMonths(i.getPersonMonths());
                im.setEstimateDetailId(ed.getEstDetailId());
                return im;
            }).collect(java.util.stream.Collectors.toList());
            edm.setEstimationItems(items);
        }
        return edm;
    }
}
