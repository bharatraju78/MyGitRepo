package com.vam.cco.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vam.cco.dao.entity.CutOffParameters;
import com.vam.cco.dao.repository.EstimateDetailRepository;
import com.vam.cco.dao.repository.EstimationItemRepository;
import com.vam.cco.dao.repository.TeaEstimateRepository;
import com.vam.cco.model.EstimateDetailModel;
import com.vam.cco.model.EstimationItemModel;
import com.vam.cco.model.ResourceDetailModel;
import com.vam.cco.model.ResourceInfoModel;
import com.vam.cco.services.CutOffParametersService;
import com.vam.cco.services.EstimationItemService;
import com.vam.cco.util.EmployeeTypeEnum;
import com.vam.cco.util.LocationEnum;
import com.vam.cco.util.RoleTypeEnum;
import com.vam.cco.util.ShiftEnum;

@Service
public class EstimationItemServiceImpl implements EstimationItemService {

    private final CutOffParametersService cutOffParametersService;
    
    @Autowired
    public EstimationItemServiceImpl(EstimationItemRepository itemRepo, EstimateDetailRepository detailRepo, TeaEstimateServiceImpl teaEstimateService, TeaEstimateRepository teaEstimateRepo, CutOffParametersService cutOffParametersService) {
        this.cutOffParametersService = cutOffParametersService;
    }

    @Override
    @Transactional
    public EstimateDetailModel addItemToDetail(EstimateDetailModel detailModel) {
    	EstimationItemModel estimationItemModel = instantiateEstimateItem();
        // set parent
    	estimationItemModel.setEstimateDetailId(detailModel.getEstDetailId());
        detailModel.getEstimationItems().add(estimationItemModel);
        return detailModel;
     }

	private EstimationItemModel instantiateEstimateItem() {
		EstimationItemModel estimationItemModel = new EstimationItemModel();
        // map fields from itemModel (only basics)
    	estimationItemModel.setTrack("");
    	estimationItemModel.setLineItem("");
    	estimationItemModel.setClassification("");
    	estimationItemModel.setAssumptionReference("");
    	
    	estimationItemModel.setOptimisticPersonHours(0.0);
    	estimationItemModel.setPessimisticPersonHours(0.0);
    	estimationItemModel.setMeanPersonHours(0.0);
    	
    	estimationItemModel.setThreePointEstimate(0.0);
    	
    	estimationItemModel.setEffortInPersonHours(0.0);
    	
    	estimationItemModel.setProjectManagementEffort(0.0);
    	estimationItemModel.setBusinessAnalysisEffort(0.0);
    	estimationItemModel.setDevelopmentEffort(0.0);
    	estimationItemModel.setQualityAssuranceEffort(0.0);
    	estimationItemModel.setAssetsAndAcceleratorsEffort(0.0);
    	estimationItemModel.setGenAIEffort(0.0);
    	
    	estimationItemModel.setPersonHours(0.0);
    	estimationItemModel.setPersonDays(0.0);
    	estimationItemModel.setPersonMonths(0.0);
		return estimationItemModel;
	}

    @Override
    public EstimateDetailModel removeItemFromDetail(EstimateDetailModel detailModel, int index) {
        try {
        	EstimationItemModel model = detailModel.getEstimationItems().get(index);
        	System.out.println("Removing item at index " + index + " with ID " + model.getAssumptionReference());
            detailModel.getEstimationItems().remove(index);
            return detailModel;
        } catch (Exception ole) {
            throw ole;
        }
    }

	@Override
	public EstimateDetailModel calculate(EstimateDetailModel detailModel) {
		List<EstimationItemModel> items = detailModel.getEstimationItems();
		double totalThreePointEstimate = 0.0d;
    	double totalEffortInPersonHours = 0.0d;
    	
    	double totalProjectManagementEffort = 0.0d;
    	double totalBusinessAnalysisEffort = 0.0d;
    	double totalDevelopmentEffort = 0.0d;
    	double totalQualityAssuranceEffort = 0.0d;
    	double totalAssetsAndAcceleratorsEffort = 0.0d;
    	double totalGenAIEffort = 0.0d;
    	
    	double totalPersonHours = 0.0d;
    	double totalPersonDays = 0.0d;
    	double totalPersonMonths = 0.0d;
    	
    	
		for (EstimationItemModel item : items) {
			totalThreePointEstimate += item.getThreePointEstimate();
			
			// apply cutoff percentages (same logic as saveFromModel)
            
            if (item.getClassification() != null) {
                CutOffParameters cof = getCutoffParametersByName(item.getClassification().trim());
                if (cof != null) {
                    double cutPct = cof.getCutPercentage();
                    if (cutPct != 0) {
                        double effort = Math.round(totalThreePointEstimate / cutPct);
                        item.setEffortInPersonHours(effort);
                        item.setProjectManagementEffort(Math.round(effort * cof.getProjectManagerPercentage()));
                        item.setBusinessAnalysisEffort(Math.round(effort * cof.getBusinessAnalystPercentage()));
                        item.setDevelopmentEffort(Math.round(effort * cof.getDevelopmentPercentage()));
                        item.setQualityAssuranceEffort(Math.round(effort * cof.getQualityAssurancePercentage()));
                        item.setAssetsAndAcceleratorsEffort(Math.round(effort * cof.getAssetsAndAcceleratorPercentage()));
                        item.setGenAIEffort(Math.round(effort * cof.getGenAIPercentage()));
                        double personHours = Math.round(item.getProjectManagementEffort() + item.getBusinessAnalysisEffort()
								+ item.getDevelopmentEffort() + item.getQualityAssuranceEffort());
                        item.setPersonHours(Math.round(personHours));
                        item.setPersonDays(Math.round(personHours / 8));// assuming 8 hours per day
                    	item.setPersonMonths(Math.round(personHours / 160));// assuming 160 hours per month
                    }
                }
            }
	    	
			totalEffortInPersonHours += item.getEffortInPersonHours();
	    	
			totalProjectManagementEffort += item.getProjectManagementEffort();
			totalBusinessAnalysisEffort += item.getBusinessAnalysisEffort();
			totalDevelopmentEffort += item.getDevelopmentEffort();
			totalQualityAssuranceEffort += item.getQualityAssuranceEffort();
			totalAssetsAndAcceleratorsEffort += item.getAssetsAndAcceleratorsEffort();
			totalGenAIEffort += item.getGenAIEffort();
	    	
			totalPersonHours += item.getPersonHours();
			totalPersonDays += item.getPersonDays();
			totalPersonMonths += item.getPersonMonths();
		}
		
		detailModel.setTotalThreePointEstimate(totalThreePointEstimate);
		
		detailModel.setTotalEffortInPersonHours(totalEffortInPersonHours);
		
		detailModel.setTotalProjectManagementEffort(totalProjectManagementEffort);
		detailModel.setTotalBusinessAnalysisEffort(totalBusinessAnalysisEffort);
		detailModel.setTotalDevelopmentEffort(totalDevelopmentEffort);
		detailModel.setTotalQualityAssuranceEffort(totalQualityAssuranceEffort);
		detailModel.setTotalAssetsAndAcceleratorsEffort(totalAssetsAndAcceleratorsEffort);
		detailModel.setTotalGenAIEffort(totalGenAIEffort);
		
		detailModel.setTotalPersonHours(totalPersonHours);
		detailModel.setTotalPersonDays(totalPersonDays);
		detailModel.setTotalPersonMonths(totalPersonMonths);
		return detailModel;
	}
	
	public CutOffParameters getCutoffParametersByName(String name) {
		return cutOffParametersService.findByName(name);
	}
	
	@Override
	public double getCutoffPercentageByName(String name) {
		double cutPct = 0.0;
		 CutOffParameters cof = getCutoffParametersByName(name);
		 if(cof != null) {
			 cutPct = cof.getCutPercentage();
		 }
		 return cutPct;
	}

	@Override
	public void getDataFromMasters(EstimateDetailModel detailModel, EstimationItemModel itemModel) {
		
		
	}
	
}