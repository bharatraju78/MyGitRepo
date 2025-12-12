package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.Practice;
import com.vam.cco.dao.repository.PracticeRepository;
import com.vam.cco.model.PracticeModel;
import com.vam.cco.services.PracticeService;

@Service
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository practiceRepository;

    @Autowired
    public PracticeServiceImpl(PracticeRepository practiceRepository) {
        this.practiceRepository = practiceRepository;
    }

    @Override
    public Practice save(Practice practice) {
        return practiceRepository.save(practice);
    }

    @Override
    public Optional<Practice> findById(Long id) {
        return practiceRepository.findById(id);
    }

    @Override
    public List<Practice> findAll() {
        return practiceRepository.findAll(Sort.by(Sort.Direction.ASC, "practiceName"));
    }

    @Override
    public Practice update(Practice practice) {
        return practiceRepository.save(practice);
    }

    @Override
    public void deleteById(Long id) {
        practiceRepository.deleteById(id);
    }

    @Override
    public Page<Practice> findAll(Pageable pageable) {
        return practiceRepository.findAll(pageable);
    }

    @Override
    public Page<PracticeModel> findAllPractices(Pageable pageable) {
        return practiceRepository.findAllPractices(pageable);
    }
}
