package com.vam.cco.services;

import com.vam.cco.dao.entity.RateMaster;
import com.vam.cco.model.RateMasterModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RateMasterService {
    RateMaster save(RateMaster rateMaster);
    Optional<RateMaster> findById(Long id);
    List<RateMaster> findAll();
    RateMaster update(RateMaster rateMaster);
    void deleteById(Long id);

    Page<RateMaster> findAll(Pageable pageable);
    Page<RateMasterModel> findAllRates(Pageable pageable);
}
