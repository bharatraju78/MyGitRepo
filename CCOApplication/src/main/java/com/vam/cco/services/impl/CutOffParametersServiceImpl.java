package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.CutOffParameters;
import com.vam.cco.dao.repository.CutOffParametersRepository;
import com.vam.cco.model.CutOffParametersModel;
import com.vam.cco.services.CutOffParametersService;

@Service
public class CutOffParametersServiceImpl implements CutOffParametersService {
    private final CutOffParametersRepository repository;

    @Autowired
    public CutOffParametersServiceImpl(CutOffParametersRepository repository) {
        this.repository = repository;
    }

    @Override
    public CutOffParameters save(CutOffParameters co) {
        return repository.save(co);
    }

    @Override
    public Optional<CutOffParameters> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CutOffParameters> findAll() {
        return repository.findAll();
    }

    @Override
    public CutOffParameters update(CutOffParameters co) {
        return repository.save(co);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<CutOffParameters> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<CutOffParametersModel> findAllCutOffParameters(Pageable pageable) {
        return repository.findAllCutOffParameters(pageable);
    }

    @Override
    public CutOffParameters findByName(String name) {
        CutOffParameters res = repository.findByName(name);
        if (res == null) res = repository.findByNameIgnoreCase(name);
        return res;
    }
}