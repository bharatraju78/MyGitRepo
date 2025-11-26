package com.vam.cco.services;

import com.vam.cco.dao.entity.Designation;
import java.util.List;
import java.util.Optional;

public interface DesignationService {
    Designation save(Designation designation);
    Optional<Designation> findById(Long id);
    List<Designation> findAll();
    Designation update(Designation designation);
    void deleteById(Long id);
}
