package com.vam.cco.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vam.cco.dao.entity.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

}
