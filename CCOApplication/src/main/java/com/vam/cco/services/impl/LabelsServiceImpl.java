package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vam.cco.dao.entity.Labels;
import com.vam.cco.dao.repository.LabelsRepository;
import com.vam.cco.services.LabelsService;

@Service
public class LabelsServiceImpl implements LabelsService {
    private static final Logger logger = LoggerFactory.getLogger(LabelsServiceImpl.class);
    private final LabelsRepository labelsRepository;

    @Autowired
    public LabelsServiceImpl(LabelsRepository labelsRepository) {
        this.labelsRepository = labelsRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Labels save(Labels labels) {
        logger.info("Saving label: {}", labels.getLabelName());
        Labels saved = labelsRepository.save(labels);
        logger.info("Label saved with id: {}", saved.getLabelId());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Labels> findById(Long id) {
        logger.info("Finding label by id: {}", id);
        Optional<Labels> label = labelsRepository.findById(id);
        if (label.isPresent()) {
            logger.info("Label found: {}", label.get().getLabelName());
        } else {
            logger.warn("Label not found for id: {}", id);
        }
        return label;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Labels> findAll() {
        logger.info("Fetching all labels");
        List<Labels> labels = labelsRepository.findAll();
        logger.info("Total labels found: {}", labels.size());
        return labels;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Labels> findAll(Pageable pageable) {
        logger.info("Fetching labels with pagination: {}", pageable);
        return labelsRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Labels update(Labels labels) {
        logger.info("Updating label: {}", labels.getLabelName());
        Labels updated = labelsRepository.save(labels);
        logger.info("Label updated with id: {}", updated.getLabelId());
        return updated;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        logger.info("Deleting label by id: {}", id);
        labelsRepository.deleteById(id);
        logger.info("Label deleted with id: {}", id);
    }
    
    @Override
	@Transactional(readOnly = true)
	public Integer getMaxOrderNo() {
		logger.info("LabelsServiceImpl::getMaxOrderNo::start");
		Integer maxOrderNo = labelsRepository.getMaxOrderNo();
		if(null != maxOrderNo) {
			maxOrderNo = maxOrderNo + 1;
		} else {
			maxOrderNo =  1;
		}
		logger.info("LabelsServiceImpl::getMaxOrderNo::maxOrderNo = {}", maxOrderNo);
		logger.info("LabelsServiceImpl::getMaxOrderNo::end");
		return maxOrderNo;
	}
}