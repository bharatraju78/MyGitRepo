package com.vam.cco.services;

import com.vam.cco.dao.entity.Practice;
import com.vam.cco.model.PracticeModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PracticeService {
    Practice save(Practice practice);
    Optional<Practice> findById(Long id);
    List<Practice> findAll();
    Practice update(Practice practice);
    void deleteById(Long id);

    Page<Practice> findAll(Pageable pageable);
    Page<PracticeModel> findAllPractices(Pageable pageable);
}
