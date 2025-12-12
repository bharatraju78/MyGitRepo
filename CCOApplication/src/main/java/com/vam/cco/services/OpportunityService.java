package com.vam.cco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vam.cco.dao.entity.Opportunity;
import com.vam.cco.model.OpportunityModel;

public interface OpportunityService {
    Opportunity save(Opportunity opportunity);

    Optional<Opportunity> findById(Long id);

    List<Opportunity> findAll();

    Opportunity update(Opportunity opportunity);

    void deleteById(Long id);

    Page<OpportunityModel> findAllActiveOpportunities(Pageable pageable);

    Page<Opportunity> findAll(Pageable pageable);

    Page<OpportunityModel> findOpportunitiesByPortfolio(Long portfolioId, Pageable pageable);

    Page<OpportunityModel> findOpportunitiesByPortfolio(List<Long> portfolioIds, Pageable pageable);
}
