package com.vam.cco.services;

import com.vam.cco.dao.entity.Vertical;
import java.util.List;
import java.util.Optional;

public interface VerticalService {
    Vertical save(Vertical vertical);
    Optional<Vertical> findById(Long id);
    List<Vertical> findAll();
    Vertical update(Vertical vertical);
    void deleteById(Long id);
}
