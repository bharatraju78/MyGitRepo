package com.vam.cco.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vam.cco.dao.entity.WorkingHours;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {

}
