package com.vam.cco.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vam.cco.dao.entity.TeaEstimate;
import com.vam.cco.model.TeaEstimateModel;

public interface TeaEstimateRepository extends JpaRepository<TeaEstimate, Long> {

    @Query("SELECT distinct new com.vam.cco.model.TeaEstimateModel(t.id, t.clientOrProspect, t.engagementName, t.businessObjective, t.engagementType, t.estimatedBy, t.verifiedBy, t.approvedBy, t.createdBy, t.modifiedBy, t.createdDate, t.modifiedDate, t.projectStartDate, t.estimationDate, t.verificationDate, t.approvalDate) FROM TeaEstimate t ORDER BY t.engagementName")
    Page<TeaEstimateModel> findAllTeaEstimates(Pageable pageable);
}
