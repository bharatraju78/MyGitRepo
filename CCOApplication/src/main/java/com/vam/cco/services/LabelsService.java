package com.vam.cco.services;

import com.vam.cco.dao.entity.Labels;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LabelsService {
    Labels save(Labels labels);
    Optional<Labels> findById(Long id);
    List<Labels> findAll();
    Page<Labels> findAll(Pageable pageable);
    Labels update(Labels labels);
    void deleteById(Long id);
    public Integer getMaxOrderNo();
}