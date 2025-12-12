package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.Opportunity;
import com.vam.cco.dao.repository.OpportunityRepository;
import com.vam.cco.model.OpportunityModel;
import com.vam.cco.services.OpportunityService;

@Service
public class OpportunityServiceImpl implements OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private static final Logger logger = LoggerFactory.getLogger(OpportunityServiceImpl.class);

    @Autowired
    public OpportunityServiceImpl(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @Override
    public Opportunity save(Opportunity opportunity) {
        logger.info("Saving opportunity: {}", opportunity);
        return opportunityRepository.save(opportunity);
    }

    @Override
    public Optional<Opportunity> findById(Long id) {
        logger.info("Finding opportunity by id: {}", id);
        return opportunityRepository.findById(id);
    }

    @Override
    public List<Opportunity> findAll() {
        logger.info("Finding all opportunities");
        return opportunityRepository.findAll();
    }

    @Override
    public Page<Opportunity> findAll(Pageable pageable) {
        logger.info("Finding all opportunities with pagination: {}", pageable);
        return opportunityRepository.findAll(pageable);
    }

    @Override
    public Opportunity update(Opportunity opportunity) {
        logger.info("Updating opportunity: {}", opportunity);
        return opportunityRepository.save(opportunity);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting opportunity by id: {}", id);
        opportunityRepository.deleteById(id);
    }

    @Override
    public Page<OpportunityModel> findAllActiveOpportunities(Pageable pageable) {
        // For parity with Account, keep stub; custom queries can be added later.
        logger.info("findAllActiveOpportunities called");
        return Page.empty();
    }

    @Override
    public Page<OpportunityModel> findOpportunitiesByPortfolio(Long portfolioId, Pageable pageable) {
        logger.info("findOpportunitiesByPortfolio called for {}", portfolioId);
        return Page.empty();
    }

    @Override
    public Page<OpportunityModel> findOpportunitiesByPortfolio(List<Long> portfolioIds, Pageable pageable) {
        logger.info("findOpportunitiesByPortfolio called for list {}", portfolioIds);
        return Page.empty();
    }
}
