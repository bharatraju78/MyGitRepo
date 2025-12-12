package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.WorkingHours;
import com.vam.cco.dao.repository.WorkingHoursRepository;
import com.vam.cco.model.WorkingHoursModel;
import com.vam.cco.services.WorkingHoursService;

@Service
public class WorkingHoursServiceImpl implements WorkingHoursService {

    private final WorkingHoursRepository workingHoursRepository;

    @Autowired
    public WorkingHoursServiceImpl(WorkingHoursRepository workingHoursRepository) {
        this.workingHoursRepository = workingHoursRepository;
    }

    @Override
    public WorkingHours save(WorkingHours workingHours) {
        return workingHoursRepository.save(workingHours);
    }

    @Override
    public Optional<WorkingHours> findById(Long id) {
        return workingHoursRepository.findById(id);
    }

    @Override
    public List<WorkingHours> findAll() {
        return workingHoursRepository.findAll(Sort.by(Sort.Direction.ASC, "location"));
    }

    @Override
    public WorkingHours update(WorkingHours workingHours) {
        return workingHoursRepository.save(workingHours);
    }

    @Override
    public void deleteById(Long id) {
        workingHoursRepository.deleteById(id);
    }

    @Override
    public Page<WorkingHours> findAll(Pageable pageable) {
        return workingHoursRepository.findAll(pageable);
    }

    @Override
    public Page<WorkingHoursModel> findAllWorkingHours(Pageable pageable) {
        // Provide a simple projection using the model constructor like DepartmentRepository
        return workingHoursRepository.findAll(pageable).map(w -> new WorkingHoursModel(w.getWorkingHoursId(), w.getLocation(), w.getHoursPerMonth()));
    }
}
