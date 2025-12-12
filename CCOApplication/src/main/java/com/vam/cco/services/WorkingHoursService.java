package com.vam.cco.services;

import com.vam.cco.dao.entity.WorkingHours;
import com.vam.cco.model.WorkingHoursModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkingHoursService {
    WorkingHours save(WorkingHours workingHours);
    Optional<WorkingHours> findById(Long id);
    List<WorkingHours> findAll();
    WorkingHours update(WorkingHours workingHours);
    void deleteById(Long id);

    Page<WorkingHours> findAll(Pageable pageable);
    Page<WorkingHoursModel> findAllWorkingHours(Pageable pageable);
}
