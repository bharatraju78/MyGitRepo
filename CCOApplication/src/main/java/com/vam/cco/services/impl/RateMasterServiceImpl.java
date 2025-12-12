package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.RateMaster;
import com.vam.cco.dao.repository.RateMasterRepository;
import com.vam.cco.model.RateMasterModel;
import com.vam.cco.services.RateMasterService;

@Service
public class RateMasterServiceImpl implements RateMasterService {

    private final RateMasterRepository rateMasterRepository;

    @Autowired
    public RateMasterServiceImpl(RateMasterRepository rateMasterRepository) {
        this.rateMasterRepository = rateMasterRepository;
    }

    @Override
    public RateMaster save(RateMaster rateMaster) {
        return rateMasterRepository.save(rateMaster);
    }

    @Override
    public Optional<RateMaster> findById(Long id) {
        return rateMasterRepository.findById(id);
    }

    @Override
    public List<RateMaster> findAll() {
        return rateMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "rateId"));
    }

    @Override
    public RateMaster update(RateMaster rateMaster) {
        return rateMasterRepository.save(rateMaster);
    }

    @Override
    public void deleteById(Long id) {
        rateMasterRepository.deleteById(id);
    }

    @Override
    public Page<RateMaster> findAll(Pageable pageable) {
        return rateMasterRepository.findAll(pageable);
    }

    @Override
    public Page<RateMasterModel> findAllRates(Pageable pageable) {
        return rateMasterRepository.findAll(pageable).map(r -> {
            RateMasterModel m = new RateMasterModel(r.getRateId());
            m.setTechnologyServiceCenter(r.getTechnologyServiceCenter());
            m.setAccountName(r.getAccountName());
            m.setSkill(r.getSkill());
            m.setRole(r.getRole());
            m.setRateAmount(r.getRateAmount());
            m.setEffectiveFrom(r.getEffectiveFrom());
            m.setEffectiveTo(r.getEffectiveTo());
            m.setRateStatus(r.getRateStatus());
            m.setCreatedBy(r.getCreatedBy());
            m.setCreatedDate(r.getCreatedDate());
            m.setModifiedBy(r.getModifiedBy());
            m.setModifiedDate(r.getModifiedDate());
            return m;
        });
    }
}