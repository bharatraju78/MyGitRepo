package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vam.cco.dao.entity.Practice;
import com.vam.cco.model.PracticeModel;

public interface PracticeRepository extends JpaRepository<Practice, Long> {

    @Query("SELECT distinct new com.vam.cco.model.PracticeModel(p.practiceId, p.practiceCode, p.practiceName, p.practiceStatus, p.createdBy, p.modifiedBy, p.createdDate, p.modifiedDate) FROM Practice p order by p.practiceName")
    Page<PracticeModel> findAllPractices(Pageable pageable);

    @Query("SELECT distinct new com.vam.cco.model.PracticeModel(p.practiceId, p.practiceCode, p.practiceName, p.practiceStatus, p.createdBy, p.modifiedBy, p.createdDate, p.modifiedDate) FROM Practice p WHERE p.practiceStatus = :status order by p.practiceName")
    Page<PracticeModel> findPracticesByStatus(@Param("status") String status, Pageable pageable);

    @Query("SELECT distinct new com.vam.cco.model.PracticeModel(p.practiceId, p.practiceCode, p.practiceName, p.practiceStatus, p.createdBy, p.modifiedBy, p.createdDate, p.modifiedDate) FROM Practice p WHERE p.practiceCode = :code")
    List<PracticeModel> findByPracticeCode(@Param("code") String code);

    @Query("SELECT new com.vam.cco.model.PracticeModel(p.practiceId, p.practiceCode, p.practiceName, p.practiceStatus, p.createdBy, p.modifiedBy, p.createdDate, p.modifiedDate) FROM Practice p WHERE p.practiceId = :id")
    PracticeModel findByPracticeId(@Param("id") Long id);

    @Query("SELECT distinct new com.vam.cco.model.PracticeModel(p.practiceId, p.practiceCode, p.practiceName, p.practiceStatus, p.createdBy, p.modifiedBy, p.createdDate, p.modifiedDate) FROM Practice p WHERE p.practiceName = :name")
    List<PracticeModel> findByPracticeName(@Param("name") String name);
}
