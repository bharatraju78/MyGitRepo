package com.vam.cco.services;

import com.vam.cco.dao.entity.CutOffParameters;
import com.vam.cco.model.CutOffParametersModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CutOffParametersService {
    CutOffParameters save(CutOffParameters co);
    Optional<CutOffParameters> findById(Long id);
    List<CutOffParameters> findAll();
    CutOffParameters update(CutOffParameters co);
    void deleteById(Long id);

    Page<CutOffParameters> findAll(Pageable pageable);
    Page<CutOffParametersModel> findAllCutOffParameters(Pageable pageable);
    CutOffParameters findByName(String name);
}