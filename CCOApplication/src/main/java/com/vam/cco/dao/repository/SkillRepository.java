package com.vam.cco.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vam.cco.dao.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
