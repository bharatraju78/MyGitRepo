package com.vam.cco.services;

import com.vam.cco.dao.entity.Grade;
import java.util.List;
import java.util.Optional;

public interface GradeService {
    Grade save(Grade grade);
    Optional<Grade> findById(Long id);
    List<Grade> findAll();
    Grade update(Grade grade);
    void deleteById(Long id);
}
