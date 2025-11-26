package com.vam.cco.services.impl;

import com.vam.cco.dao.entity.Vertical;
import com.vam.cco.dao.repository.VerticalRepository;
import com.vam.cco.services.VerticalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VerticalServiceImpl implements VerticalService {
    private final VerticalRepository verticalRepository;

    @Autowired
    public VerticalServiceImpl(VerticalRepository verticalRepository) {
        this.verticalRepository = verticalRepository;
    }

    @Override
    public Vertical save(Vertical vertical) {
        return verticalRepository.save(vertical);
    }

    @Override
    public Optional<Vertical> findById(Long id) {
        return verticalRepository.findById(id);
    }

    @Override
    public List<Vertical> findAll() {
        return verticalRepository.findAll();
    }

    @Override
    public Vertical update(Vertical vertical) {
        return verticalRepository.save(vertical);
    }

    @Override
    public void deleteById(Long id) {
        verticalRepository.deleteById(id);
    }
}
