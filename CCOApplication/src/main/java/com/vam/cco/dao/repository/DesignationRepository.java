package com.vam.cco.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vam.cco.dao.entity.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {
}
