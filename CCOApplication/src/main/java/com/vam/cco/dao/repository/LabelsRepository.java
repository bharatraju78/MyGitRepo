package com.vam.cco.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vam.cco.dao.entity.Labels;

public interface LabelsRepository extends JpaRepository<Labels, Long> {
	
	@Query(value =  "select max(order_no) from labels", nativeQuery = true)
	Integer getMaxOrderNo();
}
