package com.vam.cco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vam.cco.dao.repository.EstimateDetailRepository;
import com.vam.cco.dao.repository.EstimationItemRepository;
import com.vam.cco.dao.repository.TeaEstimateRepository;
import com.vam.cco.services.CutOffParametersService;
import com.vam.cco.services.EstimationItemService;
import com.vam.cco.services.ResourceInfoService;
import com.vam.cco.services.impl.EstimationItemServiceImpl;
import com.vam.cco.services.impl.ResourceInfoServiceImpl;
import com.vam.cco.services.impl.TeaEstimateServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public EstimationItemService estimationItemService(EstimationItemRepository itemRepo,
                                                       EstimateDetailRepository detailRepo,
                                                       TeaEstimateServiceImpl teaEstimateService,
                                                       TeaEstimateRepository teaEstimateRepo, CutOffParametersService cutOffParametersService) {
        return new EstimationItemServiceImpl(itemRepo, detailRepo, teaEstimateService, teaEstimateRepo, cutOffParametersService);
    }
    
    @Bean
    public ResourceInfoService resourceInfoService(EstimationItemRepository itemRepo,
                                                       EstimateDetailRepository detailRepo,
                                                       TeaEstimateServiceImpl teaEstimateService,
                                                       TeaEstimateRepository teaEstimateRepo, CutOffParametersService cutOffParametersService) {
        return new ResourceInfoServiceImpl(itemRepo, detailRepo, teaEstimateService, teaEstimateRepo, cutOffParametersService);
    }
}
