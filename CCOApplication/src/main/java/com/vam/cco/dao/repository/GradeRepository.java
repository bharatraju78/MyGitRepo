package com.vam.cco.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vam.cco.dao.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
