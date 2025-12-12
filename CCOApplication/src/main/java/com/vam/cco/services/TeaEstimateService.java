package com.vam.cco.services;

import com.vam.cco.dao.entity.TeaEstimate;
import com.vam.cco.model.TeaEstimateModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeaEstimateService {
    TeaEstimate save(TeaEstimate teaEstimate);
    Optional<TeaEstimate> findById(Long id);
    List<TeaEstimate> findAll();
    TeaEstimate update(TeaEstimate teaEstimate);
    void deleteById(Long id);

    // Pageable variant used by controllers for entity lists
    Page<TeaEstimate> findAll(Pageable pageable);

    // Pageable variant returning projection models
    Page<TeaEstimateModel> findAllTeaEstimates(Pageable pageable);
}
