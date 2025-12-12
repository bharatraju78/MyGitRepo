package com.vam.cco.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vam.cco.model.EffortDetailModel;
import com.vam.cco.model.EffortInfoModel;
import com.vam.cco.model.EffortOffshoreTotalModel;
import com.vam.cco.model.EffortOnsiteTotalModel;
import com.vam.cco.services.EffortInfoService;

public class EffortInfoServiceImpl implements EffortInfoService {

	private static final Logger logger = LoggerFactory.getLogger(EffortInfoServiceImpl.class);
	
	@Autowired
    public EffortInfoServiceImpl() {
    }

    @Override
    public EffortDetailModel addEffortToDetail(EffortDetailModel detailModel) {
        try {
            EffortInfoModel effortInfoModel = new EffortInfoModel();
            effortInfoModel.setEffortDetailId(detailModel.getEffDetailId());
            detailModel.getEffortInfos().add(effortInfoModel);
        } catch (Exception e) {
            logger.error("Error while adding effort info to detail model", e);
            return detailModel;
        }
        return detailModel;
    }

    @Override
    public EffortDetailModel removeEffortFromDetail(EffortDetailModel detailModel, int index) {
        try {
            detailModel.getEffortInfos().get(index);
        } catch (IndexOutOfBoundsException e) {
            logger.error("Index out of bounds while removing effort info at index: " + index, e);
            return detailModel;
        }
        return detailModel;
    }

    @Override
    public EffortDetailModel calculate(EffortDetailModel detailModel) {
        List<EffortInfoModel> effortInfoModels = detailModel.getEffortInfos();
        if (effortInfoModels != null && !effortInfoModels.isEmpty()) {
            EffortOnsiteTotalModel onsiteTotal = detailModel.getOnsiteTotal();
            EffortOffshoreTotalModel offshoreTotal = detailModel.getOffshoreTotal();
            if (onsiteTotal == null) {
                onsiteTotal = new EffortOnsiteTotalModel();
                detailModel.setOnsiteTotal(onsiteTotal);
            }
            if (offshoreTotal == null) {
                offshoreTotal = new EffortOffshoreTotalModel();
                detailModel.setOffshoreTotal(offshoreTotal);
            }

            double totalTeamSize = 0.0;
            double totalOnsiteTeamSize = 0.0;
            double totalOffshoreTeamSize = 0.0;

            // accumulate as doubles
            double onsiteYearOneJanTotal = 0, onsiteYearOneFebTotal = 0, onsiteYearOneMarTotal = 0, onsiteYearOneAprTotal = 0, onsiteYearOneMayTotal = 0, onsiteYearOneJunTotal = 0, onsiteYearOneJulTotal = 0, onsiteYearOneAugTotal = 0, onsiteYearOneSepTotal = 0, onsiteYearOneOctTotal = 0, onsiteYearOneNovTotal = 0, onsiteYearOneDecTotal = 0;
            // ... other accumulators omitted for brevity in this prototype; real implementation should include all months for all years for both onsite and offshore

            for (EffortInfoModel effortInfoModel : effortInfoModels) {
                double year1Total = 0;
                double year2Total = 0;
                double year3Total = 0;
                double year4Total = 0;
                double overallTotal = 0;
                totalTeamSize += 1;
                if ("Onsite".equalsIgnoreCase(effortInfoModel.getLocation())) {
                    totalOnsiteTeamSize += 1;
                    onsiteYearOneJanTotal += effortInfoModel.getYearOneJan();
                    onsiteYearOneFebTotal += effortInfoModel.getYearOneFeb();
                    onsiteYearOneMarTotal += effortInfoModel.getYearOneMar();
                    // ... and so on
                } else if ("Offshore".equalsIgnoreCase(effortInfoModel.getLocation())) {
                    totalOffshoreTeamSize += 1;
                }

                year1Total = effortInfoModel.getYearOneJan() + effortInfoModel.getYearOneFeb() + effortInfoModel.getYearOneMar();
                // ... add other months
                effortInfoModel.setYearOneTotal(year1Total);
                effortInfoModel.setYearTwoTotal(year2Total);
                effortInfoModel.setYearThreeTotal(year3Total);
                effortInfoModel.setYearFourTotal(year4Total);
                effortInfoModel.setOverallTotal(overallTotal);
            }

            // set totals on onsite/offshore models (partial example)
            onsiteTotal.setYearOneJanTotal((int) onsiteYearOneJanTotal); // NOTE: OnsiteTotalModel uses ints; casting may be necessary or models replaced by EffortOnsiteTotalModel

            detailModel.setTotalTeamSize(totalTeamSize);
            detailModel.setTotalOnsiteTeamSize(totalOnsiteTeamSize);
            detailModel.setTotalOffshoreTeamSize(totalOffshoreTeamSize);
            return detailModel;
        }
        return null;
    }
}
