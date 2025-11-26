package com.vam.cco.services;

import com.vam.cco.dao.entity.Skill;
import java.util.List;
import java.util.Optional;

public interface SkillService {
    Skill save(Skill skill);
    Optional<Skill> findById(Long id);
    List<Skill> findAll();
    Skill update(Skill skill);
    void deleteById(Long id);
}
