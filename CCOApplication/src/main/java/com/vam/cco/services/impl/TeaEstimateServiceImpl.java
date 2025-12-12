package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.CutOffParameters;
import com.vam.cco.dao.entity.TeaEstimate;
import com.vam.cco.dao.repository.TeaEstimateRepository;
import com.vam.cco.mapper.TeaEstimateMapper;
import com.vam.cco.model.EstimationItemModel;
import com.vam.cco.model.TeaEstimateModel;
import com.vam.cco.services.CutOffParametersService;
import com.vam.cco.services.TeaEstimateService;

@Service
public class TeaEstimateServiceImpl implements TeaEstimateService {
    private static final Logger logger = LoggerFactory.getLogger(TeaEstimateServiceImpl.class);

    private final TeaEstimateRepository teaEstimateRepository;
    private final CutOffParametersService cutOffParametersService;

    @Autowired
    public TeaEstimateServiceImpl(TeaEstimateRepository teaEstimateRepository, CutOffParametersService cutOffParametersService) {
        this.teaEstimateRepository = teaEstimateRepository;
        this.cutOffParametersService = cutOffParametersService;
    }

    @Override
    public TeaEstimate save(TeaEstimate teaEstimate) {
        return teaEstimateRepository.save(teaEstimate);
    }

    // Convenience method to save from model
    public TeaEstimate saveFromModel(TeaEstimateModel model) {
        // Compute derived per-item efforts based on cutoffs before mapping/saving
        try {
            if (model != null && model.getEstimateDetail() != null && model.getEstimateDetail().getEstimationItems() != null) {
                for (EstimationItemModel item : model.getEstimateDetail().getEstimationItems()) {
                    try {
                        double opt = item.getOptimisticPersonHours();
                        double pes = item.getPessimisticPersonHours();
                        double mean = item.getMeanPersonHours();
                        double threePoint = item.getThreePointEstimate();
                        if (opt > 0 && pes > 0 && mean > 0 && threePoint > 0) {
                            String classification = item.getClassification();
                            if (classification != null) {
                                CutOffParameters cof = cutOffParametersService.findByName(classification.trim());
                                if (cof != null) {
                                    double cutPct = cof.getCutPercentage();
                                    if (cutPct != 0) {
                                        double x = threePoint / cutPct;
                                        item.setEffortInPersonHours(x);
                                        item.setProjectManagementEffort(x * cof.getProjectManagerPercentage());
                                        item.setBusinessAnalysisEffort(x * cof.getBusinessAnalystPercentage());
                                        item.setDevelopmentEffort(x * cof.getDevelopmentPercentage());
                                        item.setQualityAssuranceEffort(x * cof.getQualityAssurancePercentage());
                                        item.setAssetsAndAcceleratorsEffort(x * cof.getAssetsAndAcceleratorPercentage());
                                        item.setGenAIEffort(x * cof.getGenAIPercentage());
                                    } else {
                                        logger.warn("Cut percentage is zero for classification {}", classification);
                                    }
                                } else {
                                    logger.debug("No cutoff parameters found for classification {}", classification);
                                }
                            }
                        }
                    } catch (Exception e) {
                        logger.warn("Failed to compute cutoffs for item {}: {}", item == null ? "?" : item.getLineItemID(), e.getMessage());
                    }
                }
            }
        } catch (Exception ex) {
            logger.warn("Error while applying cutoffs to model before save: {}", ex.getMessage());
        }

        TeaEstimate entity = TeaEstimateMapper.toEntity(model);
        return teaEstimateRepository.save(entity);
    }

    @Override
    public Optional<TeaEstimate> findById(Long id) {
        return teaEstimateRepository.findById(id);
    }

    @Override
    public List<TeaEstimate> findAll() {
        return teaEstimateRepository.findAll(Sort.by(Sort.Direction.ASC, "engagementName"));
    }

    @Override
    public TeaEstimate update(TeaEstimate teaEstimate) {
        return teaEstimateRepository.save(teaEstimate);
    }

    // Convenience method to update from model
    public TeaEstimate updateFromModel(TeaEstimateModel model) {
        TeaEstimate entity = TeaEstimateMapper.toEntity(model);
        return teaEstimateRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        teaEstimateRepository.deleteById(id);
    }

    @Override
    public Page<TeaEstimate> findAll(Pageable pageable) {
        return teaEstimateRepository.findAll(pageable);
    }

    @Override
    public Page<TeaEstimateModel> findAllTeaEstimates(Pageable pageable) {
        return teaEstimateRepository.findAllTeaEstimates(pageable);
    }

    /**
     * Compute estimation item derived values and aggregate totals without persisting.
     * Rounds three-point estimate to nearest whole number (Math.round).
     */
    public com.vam.cco.model.EstimateDetailModel computeEstimationDetails(com.vam.cco.model.EstimateDetailModel detail) {
        if (detail == null) return null;
        try {
            double totalPM = 0, totalBA = 0, totalDev = 0, totalQA = 0, totalAnA = 0, totalGenAI = 0;
            double totalPersonHours = 0, totalPersonDays = 0, totalPersonMonths = 0, totalEffort = 0;
            if (detail.getEstimationItems() != null) {
                for (int i = 0; i < detail.getEstimationItems().size(); i++) {
                    com.vam.cco.model.EstimationItemModel item = detail.getEstimationItems().get(i);
                    try {
                        double opt = item.getOptimisticPersonHours();
                        double pes = item.getPessimisticPersonHours();
                        double mean = item.getMeanPersonHours();
                        // three-point calculation: (opt + pes + 4*mean)/6 then round to nearest whole number
                        double calc = (opt + pes + 4 * mean) / 6.0;
                        long threePoint = Math.round(calc);
                        item.setThreePointEstimate(threePoint);

                        // apply cutoff percentages (same logic as saveFromModel)
                        double x = item.getThreePointEstimate();
                        if (item.getClassification() != null) {
                            com.vam.cco.dao.entity.CutOffParameters cof = cutOffParametersService.findByName(item.getClassification().trim());
                            if (cof != null) {
                                double cutPct = cof.getCutPercentage();
                                if (cutPct != 0) {
                                    double effort = round(x / cutPct);
                                    item.setEffortInPersonHours(effort);
                                    item.setProjectManagementEffort(round(effort * cof.getProjectManagerPercentage()));
                                    item.setBusinessAnalysisEffort(round(effort * cof.getBusinessAnalystPercentage()));
                                    item.setDevelopmentEffort(round(effort * cof.getDevelopmentPercentage()));
                                    item.setQualityAssuranceEffort(round(effort * cof.getQualityAssurancePercentage()));
                                    item.setAssetsAndAcceleratorsEffort(round(effort * cof.getAssetsAndAcceleratorPercentage()));
                                    item.setGenAIEffort(round(effort * cof.getGenAIPercentage()));
                                }
                            }
                        }
                        // personHours / days / months: if not set already, derive from effortInPersonHours (assume 8 hours/day, 20 days/month)
                        double perH = item.getEffortInPersonHours();
                        item.setPersonHours(round(perH));
                        item.setPersonDays(round(perH / 8.0));
                        item.setPersonMonths(perH / (8.0 * 20.0));

                        // accumulate
                        totalPM += item.getProjectManagementEffort();
                        totalBA += item.getBusinessAnalysisEffort();
                        totalDev += item.getDevelopmentEffort();
                        totalQA += item.getQualityAssuranceEffort();
                        totalAnA += item.getAssetsAndAcceleratorsEffort();
                        totalGenAI += item.getGenAIEffort();
                        totalPersonHours += item.getPersonHours();
                        totalPersonDays += item.getPersonDays();
                        totalPersonMonths += item.getPersonMonths();
                        totalEffort += item.getEffortInPersonHours();
                    } catch (Exception e) {
                        logger.warn("Failed to compute item {}: {}", item == null ? "?" : item.getLineItemID(), e.getMessage());
                    }
                }
            }
            detail.setTotalProjectManagementEffort(totalPM);
            detail.setTotalBusinessAnalysisEffort(totalBA);
            detail.setTotalDevelopmentEffort(totalDev);
            detail.setTotalQualityAssuranceEffort(totalQA);
            detail.setTotalAssetsAndAcceleratorsEffort(totalAnA);
            detail.setTotalGenAIEffort(totalGenAI);
            detail.setTotalPersonHours(totalPersonHours);
            detail.setTotalPersonDays(totalPersonDays);
            detail.setTotalPersonMonths(totalPersonMonths);
            detail.setTotalEffortInPersonHours(totalEffort);
        } catch (Exception ex) {
            logger.warn("Error while computing estimation details: {}", ex.getMessage());
        }
        return detail;
    }

	private double round(double d) {
		
		return Math.round(d);
	}

}