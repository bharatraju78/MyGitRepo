package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.Designation;
import com.vam.cco.dao.repository.DesignationRepository;
import com.vam.cco.services.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {
    private final DesignationRepository designationRepository;

    @Autowired
    public DesignationServiceImpl(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }

    @Override
    public Designation save(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public Optional<Designation> findById(Long id) {
        return designationRepository.findById(id);
    }

    @Override
    public List<Designation> findAll() {
        return designationRepository.findAll(Sort.by(Sort.Direction.ASC, "designationName"));
    }

    @Override
    public Designation update(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public void deleteById(Long id) {
        designationRepository.deleteById(id);
    }
}
