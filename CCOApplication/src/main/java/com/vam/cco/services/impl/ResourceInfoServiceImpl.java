package com.vam.cco.services.impl;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vam.cco.dao.repository.EstimateDetailRepository;
import com.vam.cco.dao.repository.EstimationItemRepository;
import com.vam.cco.dao.repository.TeaEstimateRepository;
import com.vam.cco.model.OffshoreTotalModel;
import com.vam.cco.model.OnsiteTotalModel;
import com.vam.cco.model.ResourceDetailModel;
import com.vam.cco.model.ResourceInfoModel;
import com.vam.cco.services.CutOffParametersService;
import com.vam.cco.services.ResourceInfoService;
import com.vam.cco.util.EmployeeTypeEnum;
import com.vam.cco.util.LocationEnum;
import com.vam.cco.util.RoleTypeEnum;
import com.vam.cco.util.ShiftEnum;

public class ResourceInfoServiceImpl implements ResourceInfoService {

	Logger logger = org.apache.logging.log4j.LogManager.getLogger(ResourceInfoServiceImpl.class);
	
	@Autowired
    public ResourceInfoServiceImpl(EstimationItemRepository itemRepo, EstimateDetailRepository detailRepo, TeaEstimateServiceImpl teaEstimateService, TeaEstimateRepository teaEstimateRepo, CutOffParametersService cutOffParametersService) {
    }
	
	@Override
	public ResourceDetailModel addResourceToDetail(ResourceDetailModel detailModel) {
		try {
			ResourceInfoModel resourceInfoModel = new ResourceInfoModel();
			// set parent
			resourceInfoModel.setResourceDetailId(detailModel.getResDetailId());
			getDataFromMasters(detailModel, resourceInfoModel);
			detailModel.getResourceInfos().add(resourceInfoModel);
		} catch (Exception e) {
			logger.error("Error while adding resource info to detail model", e);
			return detailModel;
		}
		return detailModel;
	}

	@Override
	public ResourceDetailModel removeResourceFromDetail(ResourceDetailModel detailModel, int index) {
		try {
			detailModel.getResourceInfos().remove(index);
			getDataFromMasters(detailModel, null);
		} catch (IndexOutOfBoundsException e) {
			logger.error("Index out of bounds while removing resource info at index: " + index, e);
			return detailModel;
		}
		return detailModel;
	}

	@Override
	public ResourceDetailModel calculate(ResourceDetailModel detailModel) {
		List<ResourceInfoModel> resourceInfoModels = detailModel.getResourceInfos();
		if(resourceInfoModels != null && !resourceInfoModels.isEmpty()) {
			OnsiteTotalModel onsiteTotal = detailModel.getOnsiteTotal();
			OffshoreTotalModel offshoreTotal = detailModel.getOffshoreTotal();
			if(onsiteTotal == null) {
				onsiteTotal = new OnsiteTotalModel();
				detailModel.setOnsiteTotal(onsiteTotal);
			}
			if(offshoreTotal == null) {
				offshoreTotal = new OffshoreTotalModel();
				detailModel.setOffshoreTotal(offshoreTotal);
			}
			
			double totalTeamSize = 0.0;
			double totalOnsiteTeamSize = 0.0;
			double totalOffshoreTeamSize = 0.0;
			
			int onsiteYearOneJanTotal = 0, onsiteYearOneFebTotal = 0, onsiteYearOneMarTotal = 0, onsiteYearOneAprTotal = 0, onsiteYearOneMayTotal = 0, onsiteYearOneJunTotal = 0, onsiteYearOneJulTotal = 0, onsiteYearOneAugTotal = 0, onsiteYearOneSepTotal = 0, onsiteYearOneOctTotal = 0, onsiteYearOneNovTotal = 0, onsiteYearOneDecTotal = 0;
			int onsiteYearTwoJanTotal = 0, onsiteYearTwoFebTotal = 0, onsiteYearTwoMarTotal = 0, onsiteYearTwoAprTotal = 0, onsiteYearTwoMayTotal = 0, onsiteYearTwoJunTotal = 0, onsiteYearTwoJulTotal = 0, onsiteYearTwoAugTotal = 0, onsiteYearTwoSepTotal = 0, onsiteYearTwoOctTotal = 0, onsiteYearTwoNovTotal = 0, onsiteYearTwoDecTotal = 0;
			int onsiteYearThreeJanTotal = 0, onsiteYearThreeFebTotal = 0, onsiteYearThreeMarTotal = 0, onsiteYearThreeAprTotal = 0, onsiteYearThreeMayTotal = 0, onsiteYearThreeJunTotal = 0, onsiteYearThreeJulTotal = 0, onsiteYearThreeAugTotal = 0, onsiteYearThreeSepTotal = 0, onsiteYearThreeOctTotal = 0, onsiteYearThreeNovTotal = 0, onsiteYearThreeDecTotal = 0;
			int onsiteYearFourJanTotal = 0, onsiteYearFourFebTotal = 0, onsiteYearFourMarTotal = 0, onsiteYearFourAprTotal = 0, onsiteYearFourMayTotal = 0, onsiteYearFourJunTotal = 0, onsiteYearFourJulTotal = 0, onsiteYearFourAugTotal = 0, onsiteYearFourSepTotal = 0, onsiteYearFourOctTotal = 0, onsiteYearFourNovTotal = 0, onsiteYearFourDecTotal = 0;
			
			int offshoreYearOneJanTotal = 0, offshoreYearOneFebTotal = 0, offshoreYearOneMarTotal = 0, offshoreYearOneAprTotal = 0, offshoreYearOneMayTotal = 0, offshoreYearOneJunTotal = 0, offshoreYearOneJulTotal = 0, offshoreYearOneAugTotal = 0, offshoreYearOneSepTotal = 0, offshoreYearOneOctTotal = 0, offshoreYearOneNovTotal = 0, offshoreYearOneDecTotal = 0;
			int offshoreYearTwoJanTotal = 0, offshoreYearTwoFebTotal = 0, offshoreYearTwoMarTotal = 0, offshoreYearTwoAprTotal = 0, offshoreYearTwoMayTotal = 0, offshoreYearTwoJunTotal = 0, offshoreYearTwoJulTotal = 0, offshoreYearTwoAugTotal = 0, offshoreYearTwoSepTotal = 0, offshoreYearTwoOctTotal = 0, offshoreYearTwoNovTotal = 0, offshoreYearTwoDecTotal = 0;
			int offshoreYearThreeJanTotal = 0, offshoreYearThreeFebTotal = 0, offshoreYearThreeMarTotal = 0,offshoreYearThreeAprTotal = 0, offshoreYearThreeMayTotal = 0, offshoreYearThreeJunTotal = 0, offshoreYearThreeJulTotal = 0, offshoreYearThreeAugTotal = 0, offshoreYearThreeSepTotal = 0, offshoreYearThreeOctTotal = 0, offshoreYearThreeNovTotal = 0, offshoreYearThreeDecTotal = 0;
			int offshoreYearFourJanTotal = 0, offshoreYearFourFebTotal = 0, offshoreYearFourMarTotal = 0, offshoreYearFourAprTotal = 0, offshoreYearFourMayTotal = 0, offshoreYearFourJunTotal = 0,offshoreYearFourJulTotal = 0, offshoreYearFourAugTotal = 0, offshoreYearFourSepTotal = 0, offshoreYearFourOctTotal = 0, offshoreYearFourNovTotal = 0, offshoreYearFourDecTotal = 0;
			
			for(ResourceInfoModel resourceInfoModel : resourceInfoModels) {
				int year1Total = 0;
				int year2Total = 0;
				int year3Total = 0;
				int year4Total = 0;
				int overallTotal = 0;
				totalTeamSize += 1;
				if("Onsite".equalsIgnoreCase(resourceInfoModel.getLocation())) {
					totalOnsiteTeamSize += 1;
					onsiteYearOneJanTotal += resourceInfoModel.getYearOneJan();
					onsiteYearOneFebTotal += resourceInfoModel.getYearOneFeb();
					onsiteYearOneMarTotal += resourceInfoModel.getYearOneMar();
					onsiteYearOneAprTotal += resourceInfoModel.getYearOneApr();
					onsiteYearOneMayTotal += resourceInfoModel.getYearOneMay();
					onsiteYearOneJunTotal += resourceInfoModel.getYearOneJun();
					onsiteYearOneJulTotal += resourceInfoModel.getYearOneJul();
					onsiteYearOneAugTotal += resourceInfoModel.getYearOneAug();
					onsiteYearOneSepTotal += resourceInfoModel.getYearOneSep();
					onsiteYearOneOctTotal += resourceInfoModel.getYearOneOct();
					onsiteYearOneNovTotal += resourceInfoModel.getYearOneNov();
					onsiteYearOneDecTotal += resourceInfoModel.getYearOneDec();
					
					onsiteYearTwoJanTotal += resourceInfoModel.getYearTwoJan();
					onsiteYearTwoFebTotal += resourceInfoModel.getYearTwoFeb();
					onsiteYearTwoMarTotal += resourceInfoModel.getYearTwoMar();
					onsiteYearTwoAprTotal += resourceInfoModel.getYearTwoApr();
					onsiteYearTwoMayTotal += resourceInfoModel.getYearTwoMay();
					onsiteYearTwoJunTotal += resourceInfoModel.getYearTwoJun();
					onsiteYearTwoJulTotal += resourceInfoModel.getYearTwoJul();
					onsiteYearTwoAugTotal += resourceInfoModel.getYearTwoAug();
					onsiteYearTwoSepTotal += resourceInfoModel.getYearTwoSep();
					onsiteYearTwoOctTotal += resourceInfoModel.getYearTwoOct();
					onsiteYearTwoNovTotal += resourceInfoModel.getYearTwoNov();
					onsiteYearTwoDecTotal += resourceInfoModel.getYearTwoDec();
					
					onsiteYearThreeJanTotal += resourceInfoModel.getYearThreeJan();
					onsiteYearThreeFebTotal += resourceInfoModel.getYearThreeFeb();
					onsiteYearThreeMarTotal += resourceInfoModel.getYearThreeMar();
					onsiteYearThreeAprTotal += resourceInfoModel.getYearThreeApr();
					onsiteYearThreeMayTotal += resourceInfoModel.getYearThreeMay();
					onsiteYearThreeJunTotal += resourceInfoModel.getYearThreeJun();
					onsiteYearThreeJulTotal += resourceInfoModel.getYearThreeJul();
					onsiteYearThreeAugTotal += resourceInfoModel.getYearThreeAug();
					onsiteYearThreeSepTotal += resourceInfoModel.getYearThreeSep();
					onsiteYearThreeOctTotal += resourceInfoModel.getYearThreeOct();
					onsiteYearThreeNovTotal += resourceInfoModel.getYearThreeNov();
					onsiteYearThreeDecTotal += resourceInfoModel.getYearThreeDec();
					
					onsiteYearFourJanTotal += resourceInfoModel.getYearFourJan();
					onsiteYearFourFebTotal += resourceInfoModel.getYearFourFeb();
					onsiteYearFourMarTotal += resourceInfoModel.getYearFourMar();
					onsiteYearFourAprTotal += resourceInfoModel.getYearFourApr();
					onsiteYearFourMayTotal += resourceInfoModel.getYearFourMay();
					onsiteYearFourJunTotal += resourceInfoModel.getYearFourJun();
					onsiteYearFourJulTotal += resourceInfoModel.getYearFourJul();
					onsiteYearFourAugTotal += resourceInfoModel.getYearFourAug();
					onsiteYearFourSepTotal += resourceInfoModel.getYearFourSep();
					onsiteYearFourOctTotal += resourceInfoModel.getYearFourOct();
					onsiteYearFourNovTotal += resourceInfoModel.getYearFourNov();
					onsiteYearFourDecTotal += resourceInfoModel.getYearFourDec();
					
				} else if("Offshore".equalsIgnoreCase(resourceInfoModel.getLocation())) {
					totalOffshoreTeamSize += 1;
					offshoreYearOneJanTotal += resourceInfoModel.getYearOneJan();
					offshoreYearOneFebTotal += resourceInfoModel.getYearOneFeb();
					offshoreYearOneMarTotal += resourceInfoModel.getYearOneMar();
					offshoreYearOneAprTotal += resourceInfoModel.getYearOneApr();
					offshoreYearOneMayTotal += resourceInfoModel.getYearOneMay();
					offshoreYearOneJunTotal += resourceInfoModel.getYearOneJun();
					offshoreYearOneJulTotal += resourceInfoModel.getYearOneJul();
					offshoreYearOneAugTotal += resourceInfoModel.getYearOneAug();
					offshoreYearOneSepTotal += resourceInfoModel.getYearOneSep();
					offshoreYearOneOctTotal += resourceInfoModel.getYearOneOct();
					offshoreYearOneNovTotal += resourceInfoModel.getYearOneNov();
					offshoreYearOneDecTotal += resourceInfoModel.getYearOneDec();
					
					offshoreYearTwoJanTotal += resourceInfoModel.getYearTwoJan();
					offshoreYearTwoFebTotal += resourceInfoModel.getYearTwoFeb();
					offshoreYearTwoMarTotal += resourceInfoModel.getYearTwoMar();
					offshoreYearTwoAprTotal += resourceInfoModel.getYearTwoApr();
					offshoreYearTwoMayTotal += resourceInfoModel.getYearTwoMay();
					offshoreYearTwoJunTotal += resourceInfoModel.getYearTwoJun();
					offshoreYearTwoJulTotal += resourceInfoModel.getYearTwoJul();
					offshoreYearTwoAugTotal += resourceInfoModel.getYearTwoAug();
					offshoreYearTwoSepTotal += resourceInfoModel.getYearTwoSep();
					offshoreYearTwoOctTotal += resourceInfoModel.getYearTwoOct();
					offshoreYearTwoNovTotal += resourceInfoModel.getYearTwoNov();
					offshoreYearTwoDecTotal += resourceInfoModel.getYearTwoDec();
					
					offshoreYearThreeJanTotal += resourceInfoModel.getYearThreeJan();
					offshoreYearThreeFebTotal += resourceInfoModel.getYearThreeFeb();
					offshoreYearThreeMarTotal += resourceInfoModel.getYearThreeMar();
					offshoreYearThreeAprTotal += resourceInfoModel.getYearThreeApr();
					offshoreYearThreeMayTotal += resourceInfoModel.getYearThreeMay();
					offshoreYearThreeJunTotal += resourceInfoModel.getYearThreeJun();
					offshoreYearThreeJulTotal += resourceInfoModel.getYearThreeJul();
					offshoreYearThreeAugTotal += resourceInfoModel.getYearThreeAug();
					offshoreYearThreeSepTotal += resourceInfoModel.getYearThreeSep();
					offshoreYearThreeOctTotal += resourceInfoModel.getYearThreeOct();
					offshoreYearThreeNovTotal += resourceInfoModel.getYearThreeNov();
					offshoreYearThreeDecTotal += resourceInfoModel.getYearThreeDec();
					
					offshoreYearFourJanTotal += resourceInfoModel.getYearFourJan();
					offshoreYearFourFebTotal += resourceInfoModel.getYearFourFeb();
					offshoreYearFourMarTotal += resourceInfoModel.getYearFourMar();
					offshoreYearFourAprTotal += resourceInfoModel.getYearFourApr();
					offshoreYearFourMayTotal += resourceInfoModel.getYearFourMay();
					offshoreYearFourJunTotal += resourceInfoModel.getYearFourJun();
					offshoreYearFourJulTotal += resourceInfoModel.getYearFourJul();
					offshoreYearFourAugTotal += resourceInfoModel.getYearFourAug();
					offshoreYearFourSepTotal += resourceInfoModel.getYearFourSep();
					offshoreYearFourOctTotal += resourceInfoModel.getYearFourOct();
					offshoreYearFourNovTotal += resourceInfoModel.getYearFourNov();
					offshoreYearFourDecTotal += resourceInfoModel.getYearFourDec();
					
				}
				
				year1Total = resourceInfoModel.getYearOneJan() + resourceInfoModel.getYearOneFeb() + resourceInfoModel.getYearOneMar()
						+ resourceInfoModel.getYearOneApr() + resourceInfoModel.getYearOneMay() + resourceInfoModel.getYearOneJun()
						+ resourceInfoModel.getYearOneJul() + resourceInfoModel.getYearOneAug() + resourceInfoModel.getYearOneSep()
						+ resourceInfoModel.getYearOneOct() + resourceInfoModel.getYearOneNov() + resourceInfoModel.getYearOneDec();
				year2Total = resourceInfoModel.getYearTwoJan() + resourceInfoModel.getYearTwoFeb() + resourceInfoModel.getYearTwoMar()
						+ resourceInfoModel.getYearTwoApr() + resourceInfoModel.getYearTwoMay() + resourceInfoModel.getYearTwoJun()
						+ resourceInfoModel.getYearTwoJul() + resourceInfoModel.getYearTwoAug() + resourceInfoModel.getYearTwoSep()
						+ resourceInfoModel.getYearTwoOct() + resourceInfoModel.getYearTwoNov() + resourceInfoModel.getYearTwoDec();
				year3Total = resourceInfoModel.getYearThreeJan() + resourceInfoModel.getYearThreeFeb() + resourceInfoModel.getYearThreeMar()
						+ resourceInfoModel.getYearThreeApr() + resourceInfoModel.getYearThreeMay() + resourceInfoModel.getYearThreeJun()
						+ resourceInfoModel.getYearThreeJul() + resourceInfoModel.getYearThreeAug() + resourceInfoModel.getYearThreeSep()
						+ resourceInfoModel.getYearThreeOct() + resourceInfoModel.getYearThreeNov() + resourceInfoModel.getYearThreeDec();
				year4Total = resourceInfoModel.getYearFourJan() + resourceInfoModel.getYearFourFeb() + resourceInfoModel.getYearFourMar()
						+ resourceInfoModel.getYearFourApr() + resourceInfoModel.getYearFourMay()+ resourceInfoModel.getYearFourJun()
						+ resourceInfoModel.getYearFourJul() + resourceInfoModel.getYearFourAug() + resourceInfoModel.getYearFourSep()
						+ resourceInfoModel.getYearFourOct() + resourceInfoModel.getYearFourNov() + resourceInfoModel.getYearFourDec();
				
				overallTotal = year1Total + year2Total + year3Total + year4Total;
				
				resourceInfoModel.setYearOneTotal(year1Total);
				resourceInfoModel.setYearTwoTotal(year2Total);
				resourceInfoModel.setYearThreeTotal(year3Total);
				resourceInfoModel.setYearFourTotal(year4Total);
				resourceInfoModel.setOverallTotal(overallTotal);
				
			}
			
			setYearOneOnsiteTotalModel(onsiteTotal, onsiteYearOneJanTotal, onsiteYearOneFebTotal, onsiteYearOneMarTotal,
					onsiteYearOneAprTotal, onsiteYearOneMayTotal, onsiteYearOneJunTotal, onsiteYearOneJulTotal,
					onsiteYearOneAugTotal, onsiteYearOneSepTotal, onsiteYearOneOctTotal, onsiteYearOneNovTotal,
					onsiteYearOneDecTotal);
			
			setYearTwoOnsiteTotalModel(onsiteTotal, onsiteYearTwoJanTotal, onsiteYearTwoFebTotal, onsiteYearTwoMarTotal,
					onsiteYearTwoAprTotal, onsiteYearTwoMayTotal, onsiteYearTwoJunTotal, onsiteYearTwoJulTotal,
					onsiteYearTwoAugTotal, onsiteYearTwoSepTotal, onsiteYearTwoOctTotal, onsiteYearTwoNovTotal,
					onsiteYearTwoDecTotal);
			
			setYearThreeOnsiteTotalModel(onsiteTotal, onsiteYearThreeJanTotal, onsiteYearThreeFebTotal,
					onsiteYearThreeMarTotal, onsiteYearThreeAprTotal, onsiteYearThreeMayTotal, onsiteYearThreeJunTotal,
					onsiteYearThreeJulTotal, onsiteYearThreeAugTotal, onsiteYearThreeSepTotal, onsiteYearThreeOctTotal,
					onsiteYearThreeNovTotal, onsiteYearThreeDecTotal);
			
			setYearFourOnsiteTotalModel(onsiteTotal, onsiteYearFourJanTotal, onsiteYearFourFebTotal,
					onsiteYearFourMarTotal, onsiteYearFourAprTotal, onsiteYearFourMayTotal, onsiteYearFourJunTotal,
					onsiteYearFourJulTotal, onsiteYearFourAugTotal, onsiteYearFourSepTotal, onsiteYearFourOctTotal,
					onsiteYearFourNovTotal, onsiteYearFourDecTotal);
			
			onsiteTotal.setOverallTotal(onsiteTotal.getYearOneOverallTotal() + onsiteTotal.getYearTwoOverallTotal() + onsiteTotal.getYearThreeOverallTotal() + onsiteTotal.getYearFourOverallTotal());
			
			setYearOneOffshoreTotalModel(offshoreTotal, offshoreYearOneJanTotal, offshoreYearOneFebTotal,
					offshoreYearOneMarTotal, offshoreYearOneAprTotal, offshoreYearOneMayTotal, offshoreYearOneJunTotal,
					offshoreYearOneJulTotal, offshoreYearOneAugTotal, offshoreYearOneSepTotal, offshoreYearOneOctTotal,
					offshoreYearOneNovTotal, offshoreYearOneDecTotal);
			
			setYearTwoOffshoreTotalModel(offshoreTotal, offshoreYearTwoJanTotal, offshoreYearTwoFebTotal,
					offshoreYearTwoMarTotal, offshoreYearTwoAprTotal, offshoreYearTwoMayTotal, offshoreYearTwoJunTotal,
					offshoreYearTwoJulTotal, offshoreYearTwoAugTotal, offshoreYearTwoSepTotal, offshoreYearTwoOctTotal,
					offshoreYearTwoNovTotal, offshoreYearTwoDecTotal);
			
			setYearThreeOffshoreTotalModel(offshoreTotal, offshoreYearThreeJanTotal, offshoreYearThreeFebTotal,
					offshoreYearThreeMarTotal, offshoreYearThreeAprTotal, offshoreYearThreeMayTotal,
					offshoreYearThreeJunTotal, offshoreYearThreeJulTotal, offshoreYearThreeAugTotal,
					offshoreYearThreeSepTotal, offshoreYearThreeOctTotal, offshoreYearThreeNovTotal,
					offshoreYearThreeDecTotal);
			
			setYearFourOffshoreTotalModel(offshoreTotal, offshoreYearFourJanTotal, offshoreYearFourFebTotal,
					offshoreYearFourMarTotal, offshoreYearFourAprTotal, offshoreYearFourMayTotal,
					offshoreYearFourJunTotal, offshoreYearFourJulTotal, offshoreYearFourAugTotal,
					offshoreYearFourSepTotal, offshoreYearFourOctTotal, offshoreYearFourNovTotal,
					offshoreYearFourDecTotal);
			
			offshoreTotal.setOverallTotal(offshoreTotal.getYearOneOverallTotal() + offshoreTotal.getYearTwoOverallTotal() + offshoreTotal.getYearThreeOverallTotal() + offshoreTotal.getYearFourOverallTotal());
			
			setTotalModel(onsiteTotal, offshoreTotal);
			
			detailModel.setTotalTeamSize(totalTeamSize);
			detailModel.setTotalOnsiteTeamSize(totalOnsiteTeamSize);
			detailModel.setTotalOffshoreTeamSize(totalOffshoreTeamSize);
			return detailModel;
		}
		
		return null;
	}

	private void setTotalModel(OnsiteTotalModel onsiteTotal, OffshoreTotalModel offshoreTotal) {
		// TODO Auto-generated method stub
		
	}

	private void setYearFourOffshoreTotalModel(OffshoreTotalModel offshoreTotal, int offshoreYearFourJanTotal,
			int offshoreYearFourFebTotal, int offshoreYearFourMarTotal, int offshoreYearFourAprTotal,
			int offshoreYearFourMayTotal, int offshoreYearFourJunTotal, int offshoreYearFourJulTotal,
			int offshoreYearFourAugTotal, int offshoreYearFourSepTotal, int offshoreYearFourOctTotal,
			int offshoreYearFourNovTotal, int offshoreYearFourDecTotal) {
		offshoreTotal.setYearFourJanTotal(offshoreYearFourJanTotal);
		offshoreTotal.setYearFourFebTotal(offshoreYearFourFebTotal);
		offshoreTotal.setYearFourMarTotal(offshoreYearFourMarTotal);
		offshoreTotal.setYearFourAprTotal(offshoreYearFourAprTotal);
		offshoreTotal.setYearFourMayTotal(offshoreYearFourMayTotal);
		offshoreTotal.setYearFourJunTotal(offshoreYearFourJunTotal);
		offshoreTotal.setYearFourJulTotal(offshoreYearFourJulTotal);
		offshoreTotal.setYearFourAugTotal(offshoreYearFourAugTotal);
		offshoreTotal.setYearFourSepTotal(offshoreYearFourSepTotal);
		offshoreTotal.setYearFourOctTotal(offshoreYearFourOctTotal);
		offshoreTotal.setYearFourNovTotal(offshoreYearFourNovTotal);
		offshoreTotal.setYearFourDecTotal(offshoreYearFourDecTotal);
		offshoreTotal.setYearFourOverallTotal(offshoreYearFourJanTotal + offshoreYearFourFebTotal + offshoreYearFourMarTotal + offshoreYearFourAprTotal + offshoreYearFourMayTotal + offshoreYearFourJunTotal + offshoreYearFourJulTotal + offshoreYearFourAugTotal + offshoreYearFourSepTotal + offshoreYearFourOctTotal + offshoreYearFourNovTotal + offshoreYearFourDecTotal);
	}

	private void setYearThreeOffshoreTotalModel(OffshoreTotalModel offshoreTotal, int offshoreYearThreeJanTotal,
			int offshoreYearThreeFebTotal, int offshoreYearThreeMarTotal, int offshoreYearThreeAprTotal,
			int offshoreYearThreeMayTotal, int offshoreYearThreeJunTotal, int offshoreYearThreeJulTotal,
			int offshoreYearThreeAugTotal, int offshoreYearThreeSepTotal, int offshoreYearThreeOctTotal,
			int offshoreYearThreeNovTotal, int offshoreYearThreeDecTotal) {
		offshoreTotal.setYearThreeJanTotal(offshoreYearThreeJanTotal);
		offshoreTotal.setYearThreeFebTotal(offshoreYearThreeFebTotal);
		offshoreTotal.setYearThreeMarTotal(offshoreYearThreeMarTotal);
		offshoreTotal.setYearThreeAprTotal(offshoreYearThreeAprTotal);
		offshoreTotal.setYearThreeMayTotal(offshoreYearThreeMayTotal);
		offshoreTotal.setYearThreeJunTotal(offshoreYearThreeJunTotal);
		offshoreTotal.setYearThreeJulTotal(offshoreYearThreeJulTotal);
		offshoreTotal.setYearThreeAugTotal(offshoreYearThreeAugTotal);
		offshoreTotal.setYearThreeSepTotal(offshoreYearThreeSepTotal);
		offshoreTotal.setYearThreeOctTotal(offshoreYearThreeOctTotal);
		offshoreTotal.setYearThreeNovTotal(offshoreYearThreeNovTotal);
		offshoreTotal.setYearThreeDecTotal(offshoreYearThreeDecTotal);
		offshoreTotal.setYearThreeOverallTotal(offshoreYearThreeJanTotal + offshoreYearThreeFebTotal + offshoreYearThreeMarTotal + offshoreYearThreeAprTotal + offshoreYearThreeMayTotal + offshoreYearThreeJunTotal + offshoreYearThreeJulTotal + offshoreYearThreeAugTotal + offshoreYearThreeSepTotal + offshoreYearThreeOctTotal + offshoreYearThreeNovTotal + offshoreYearThreeDecTotal);
	}

	private void setYearTwoOffshoreTotalModel(OffshoreTotalModel offshoreTotal, int offshoreYearTwoJanTotal,
			int offshoreYearTwoFebTotal, int offshoreYearTwoMarTotal, int offshoreYearTwoAprTotal,
			int offshoreYearTwoMayTotal, int offshoreYearTwoJunTotal, int offshoreYearTwoJulTotal,
			int offshoreYearTwoAugTotal, int offshoreYearTwoSepTotal, int offshoreYearTwoOctTotal,
			int offshoreYearTwoNovTotal, int offshoreYearTwoDecTotal) {
		offshoreTotal.setYearTwoJanTotal(offshoreYearTwoJanTotal);
		offshoreTotal.setYearTwoFebTotal(offshoreYearTwoFebTotal);
		offshoreTotal.setYearTwoMarTotal(offshoreYearTwoMarTotal);
		offshoreTotal.setYearTwoAprTotal(offshoreYearTwoAprTotal);
		offshoreTotal.setYearTwoMayTotal(offshoreYearTwoMayTotal);
		offshoreTotal.setYearTwoJunTotal(offshoreYearTwoJunTotal);
		offshoreTotal.setYearTwoJulTotal(offshoreYearTwoJulTotal);
		offshoreTotal.setYearTwoAugTotal(offshoreYearTwoAugTotal);
		offshoreTotal.setYearTwoSepTotal(offshoreYearTwoSepTotal);
		offshoreTotal.setYearTwoOctTotal(offshoreYearTwoOctTotal);
		offshoreTotal.setYearTwoNovTotal(offshoreYearTwoNovTotal);
		offshoreTotal.setYearTwoDecTotal(offshoreYearTwoDecTotal);
		offshoreTotal.setYearTwoOverallTotal(offshoreYearTwoJanTotal + offshoreYearTwoFebTotal + offshoreYearTwoMarTotal + offshoreYearTwoAprTotal + offshoreYearTwoMayTotal + offshoreYearTwoJunTotal + offshoreYearTwoJulTotal + offshoreYearTwoAugTotal + offshoreYearTwoSepTotal + offshoreYearTwoOctTotal + offshoreYearTwoNovTotal + offshoreYearTwoDecTotal);
	}

	private void setYearOneOffshoreTotalModel(OffshoreTotalModel offshoreTotal, int offshoreYearOneJanTotal,
			int offshoreYearOneFebTotal, int offshoreYearOneMarTotal, int offshoreYearOneAprTotal,
			int offshoreYearOneMayTotal, int offshoreYearOneJunTotal, int offshoreYearOneJulTotal,
			int offshoreYearOneAugTotal, int offshoreYearOneSepTotal, int offshoreYearOneOctTotal,
			int offshoreYearOneNovTotal, int offshoreYearOneDecTotal) {
		offshoreTotal.setYearOneJanTotal(offshoreYearOneJanTotal);
		offshoreTotal.setYearOneFebTotal(offshoreYearOneFebTotal);
		offshoreTotal.setYearOneMarTotal(offshoreYearOneMarTotal);
		offshoreTotal.setYearOneAprTotal(offshoreYearOneAprTotal);
		offshoreTotal.setYearOneMayTotal(offshoreYearOneMayTotal);
		offshoreTotal.setYearOneJunTotal(offshoreYearOneJunTotal);
		offshoreTotal.setYearOneJulTotal(offshoreYearOneJulTotal);
		offshoreTotal.setYearOneAugTotal(offshoreYearOneAugTotal);
		offshoreTotal.setYearOneSepTotal(offshoreYearOneSepTotal);
		offshoreTotal.setYearOneOctTotal(offshoreYearOneOctTotal);
		offshoreTotal.setYearOneNovTotal(offshoreYearOneNovTotal);
		offshoreTotal.setYearOneDecTotal(offshoreYearOneDecTotal);
		offshoreTotal.setYearOneOverallTotal(offshoreYearOneJanTotal + offshoreYearOneFebTotal + offshoreYearOneMarTotal + offshoreYearOneAprTotal + offshoreYearOneMayTotal + offshoreYearOneJunTotal + offshoreYearOneJulTotal + offshoreYearOneAugTotal + offshoreYearOneSepTotal + offshoreYearOneOctTotal + offshoreYearOneNovTotal + offshoreYearOneDecTotal);
	}

	private void setYearFourOnsiteTotalModel(OnsiteTotalModel onsiteTotal, int onsiteYearFourJanTotal,
			int onsiteYearFourFebTotal, int onsiteYearFourMarTotal, int onsiteYearFourAprTotal,
			int onsiteYearFourMayTotal, int onsiteYearFourJunTotal, int onsiteYearFourJulTotal,
			int onsiteYearFourAugTotal, int onsiteYearFourSepTotal, int onsiteYearFourOctTotal,
			int onsiteYearFourNovTotal, int onsiteYearFourDecTotal) {
		onsiteTotal.setYearFourJanTotal(onsiteYearFourJanTotal);
		onsiteTotal.setYearFourFebTotal(onsiteYearFourFebTotal);
		onsiteTotal.setYearFourMarTotal(onsiteYearFourMarTotal);
		onsiteTotal.setYearFourAprTotal(onsiteYearFourAprTotal);
		onsiteTotal.setYearFourMayTotal(onsiteYearFourMayTotal);
		onsiteTotal.setYearFourJunTotal(onsiteYearFourJunTotal);
		onsiteTotal.setYearFourJulTotal(onsiteYearFourJulTotal);
		onsiteTotal.setYearFourAugTotal(onsiteYearFourAugTotal);
		onsiteTotal.setYearFourSepTotal(onsiteYearFourSepTotal);
		onsiteTotal.setYearFourOctTotal(onsiteYearFourOctTotal);
		onsiteTotal.setYearFourNovTotal(onsiteYearFourNovTotal);
		onsiteTotal.setYearFourDecTotal(onsiteYearFourDecTotal);
		onsiteTotal.setYearFourOverallTotal(onsiteYearFourJanTotal + onsiteYearFourFebTotal + onsiteYearFourMarTotal + onsiteYearFourAprTotal + onsiteYearFourMayTotal + onsiteYearFourJunTotal + onsiteYearFourJulTotal + onsiteYearFourAugTotal + onsiteYearFourSepTotal + onsiteYearFourOctTotal + onsiteYearFourNovTotal + onsiteYearFourDecTotal);
	}

	private void setYearThreeOnsiteTotalModel(OnsiteTotalModel onsiteTotal, int onsiteYearThreeJanTotal,
			int onsiteYearThreeFebTotal, int onsiteYearThreeMarTotal, int onsiteYearThreeAprTotal,
			int onsiteYearThreeMayTotal, int onsiteYearThreeJunTotal, int onsiteYearThreeJulTotal,
			int onsiteYearThreeAugTotal, int onsiteYearThreeSepTotal, int onsiteYearThreeOctTotal,
			int onsiteYearThreeNovTotal, int onsiteYearThreeDecTotal) {
		onsiteTotal.setYearThreeJanTotal(onsiteYearThreeJanTotal);
		onsiteTotal.setYearThreeFebTotal(onsiteYearThreeFebTotal);
		onsiteTotal.setYearThreeMarTotal(onsiteYearThreeMarTotal);
		onsiteTotal.setYearThreeAprTotal(onsiteYearThreeAprTotal);
		onsiteTotal.setYearThreeMayTotal(onsiteYearThreeMayTotal);
		onsiteTotal.setYearThreeJunTotal(onsiteYearThreeJunTotal);
		onsiteTotal.setYearThreeJulTotal(onsiteYearThreeJulTotal);
		onsiteTotal.setYearThreeAugTotal(onsiteYearThreeAugTotal);
		onsiteTotal.setYearThreeSepTotal(onsiteYearThreeSepTotal);
		onsiteTotal.setYearThreeOctTotal(onsiteYearThreeOctTotal);
		onsiteTotal.setYearThreeNovTotal(onsiteYearThreeNovTotal);
		onsiteTotal.setYearThreeDecTotal(onsiteYearThreeDecTotal);
		onsiteTotal.setYearThreeOverallTotal(onsiteYearThreeJanTotal + onsiteYearThreeFebTotal + onsiteYearThreeMarTotal + onsiteYearThreeAprTotal + onsiteYearThreeMayTotal + onsiteYearThreeJunTotal + onsiteYearThreeJulTotal + onsiteYearThreeAugTotal + onsiteYearThreeSepTotal + onsiteYearThreeOctTotal + onsiteYearThreeNovTotal + onsiteYearThreeDecTotal);
	}

	private void setYearTwoOnsiteTotalModel(OnsiteTotalModel onsiteTotal, int onsiteYearTwoJanTotal,
			int onsiteYearTwoFebTotal, int onsiteYearTwoMarTotal, int onsiteYearTwoAprTotal, int onsiteYearTwoMayTotal,
			int onsiteYearTwoJunTotal, int onsiteYearTwoJulTotal, int onsiteYearTwoAugTotal, int onsiteYearTwoSepTotal,
			int onsiteYearTwoOctTotal, int onsiteYearTwoNovTotal, int onsiteYearTwoDecTotal) {
		onsiteTotal.setYearTwoJanTotal(onsiteYearTwoJanTotal);
		onsiteTotal.setYearTwoFebTotal(onsiteYearTwoFebTotal);
		onsiteTotal.setYearTwoMarTotal(onsiteYearTwoMarTotal);
		onsiteTotal.setYearTwoAprTotal(onsiteYearTwoAprTotal);
		onsiteTotal.setYearTwoMayTotal(onsiteYearTwoMayTotal);
		onsiteTotal.setYearTwoJunTotal(onsiteYearTwoJunTotal);
		onsiteTotal.setYearTwoJulTotal(onsiteYearTwoJulTotal);
		onsiteTotal.setYearTwoAugTotal(onsiteYearTwoAugTotal);
		onsiteTotal.setYearTwoSepTotal(onsiteYearTwoSepTotal);
		onsiteTotal.setYearTwoOctTotal(onsiteYearTwoOctTotal);
		onsiteTotal.setYearTwoNovTotal(onsiteYearTwoNovTotal);
		onsiteTotal.setYearTwoDecTotal(onsiteYearTwoDecTotal);
		onsiteTotal.setYearTwoOverallTotal(onsiteYearTwoJanTotal + onsiteYearTwoFebTotal + onsiteYearTwoMarTotal + onsiteYearTwoAprTotal + onsiteYearTwoMayTotal + onsiteYearTwoJunTotal + onsiteYearTwoJulTotal + onsiteYearTwoAugTotal + onsiteYearTwoSepTotal + onsiteYearTwoOctTotal + onsiteYearTwoNovTotal + onsiteYearTwoDecTotal);
	}

	private void setYearOneOnsiteTotalModel(OnsiteTotalModel onsiteTotal, int onsiteYearOneJanTotal,
			int onsiteYearOneFebTotal, int onsiteYearOneMarTotal, int onsiteYearOneAprTotal, int onsiteYearOneMayTotal,
			int onsiteYearOneJunTotal, int onsiteYearOneJulTotal, int onsiteYearOneAugTotal, int onsiteYearOneSepTotal,
			int onsiteYearOneOctTotal, int onsiteYearOneNovTotal, int onsiteYearOneDecTotal) {
		onsiteTotal.setYearOneJanTotal(onsiteYearOneJanTotal);
		onsiteTotal.setYearOneFebTotal(onsiteYearOneFebTotal);
		onsiteTotal.setYearOneMarTotal(onsiteYearOneMarTotal);
		onsiteTotal.setYearOneAprTotal(onsiteYearOneAprTotal);
		onsiteTotal.setYearOneMayTotal(onsiteYearOneMayTotal);
		onsiteTotal.setYearOneJunTotal(onsiteYearOneJunTotal);
		onsiteTotal.setYearOneJulTotal(onsiteYearOneJulTotal);
		onsiteTotal.setYearOneAugTotal(onsiteYearOneAugTotal);
		onsiteTotal.setYearOneSepTotal(onsiteYearOneSepTotal);
		onsiteTotal.setYearOneOctTotal(onsiteYearOneOctTotal);
		onsiteTotal.setYearOneNovTotal(onsiteYearOneNovTotal);
		onsiteTotal.setYearOneDecTotal(onsiteYearOneDecTotal);
		onsiteTotal.setYearOneOverallTotal(onsiteYearOneJanTotal + onsiteYearOneFebTotal + onsiteYearOneMarTotal + onsiteYearOneAprTotal + onsiteYearOneMayTotal + onsiteYearOneJunTotal + onsiteYearOneJulTotal + onsiteYearOneAugTotal + onsiteYearOneSepTotal + onsiteYearOneOctTotal + onsiteYearOneNovTotal + onsiteYearOneDecTotal);
	}

	@Override
	public void getDataFromMasters(ResourceDetailModel detailModel, ResourceInfoModel infoModel) {
		detailModel.setLocationList(LocationEnum.getAllLocations());
		detailModel.setRoleTypeList(RoleTypeEnum.getAllRoleTypes());
		detailModel.setShiftList(ShiftEnum.getAllShifts());
		detailModel.setEmployeeTypeList(EmployeeTypeEnum.getAllEmployeeTypes());
		if(infoModel !=null) {
			infoModel.setLocation(LocationEnum.BLANK.getValue());
			infoModel.setRoleType(RoleTypeEnum.BLANK.getValue());
			infoModel.setShift(ShiftEnum.BLANK.getValue());
			infoModel.setEmployeeType(EmployeeTypeEnum.BLANK.getValue());
		}
	}

}
