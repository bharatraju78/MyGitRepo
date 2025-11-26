package com.vam.cco.dao.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.EmpUpdateRequest;
import com.vam.cco.dao.repository.EmpUpdateRequestRepository;

@Service
public class EmpUpdateRequestService {

    @Autowired
    private EmpUpdateRequestRepository empUpdateRequestRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmpUpdateRequestService.class);

    public EmpUpdateRequest save(EmpUpdateRequest request) {
        logger.info("Saving EmpUpdateRequest: {}", request);
        return empUpdateRequestRepository.save(request);
    }

    public List<EmpUpdateRequest> findAll() {
        logger.info("Fetching all EmpUpdateRequests");
        return empUpdateRequestRepository.findAll();
    }

    public Optional<EmpUpdateRequest> findById(Long id) {
        logger.info("Fetching EmpUpdateRequest by id: {}", id);
        return empUpdateRequestRepository.findById(id);
    }

    public EmpUpdateRequest update(EmpUpdateRequest request) {
        logger.info("Updating EmpUpdateRequest: {}", request);
        return empUpdateRequestRepository.save(request);
    }

    public void deleteById(Long id) {
        logger.info("Deleting EmpUpdateRequest by id: {}", id);
        empUpdateRequestRepository.deleteById(id);
    }
    
    public List<EmpUpdateRequest> findByRequestById(Long requestById) {
		logger.info("Fetching EmpUpdateRequests by requestById: {}", requestById);
		return empUpdateRequestRepository.findByRequestById(requestById);
	}
    
    public List<EmpUpdateRequest> findByRequestToId(Long requestToId) {
		logger.info("Fetching EmpUpdateRequests by requestToId: {}", requestToId);
		return empUpdateRequestRepository.findByRequestToId(requestToId);
	}
    
    public Page<EmpUpdateRequest> findByStatusReqTo(Long employeeId, String status, Pageable pageable) {
		logger.info("Fetching EmpUpdateRequests by employeeId: {} and status: {}", employeeId, status);
		return empUpdateRequestRepository.findByStatusReqTo(employeeId, status, pageable);
	}
    
    public Page<EmpUpdateRequest> findByStatusReqBy(Long employeeId, String status, Pageable pageable){
		logger.info("Fetching EmpUpdateRequests by employeeId: {} and status: {}", employeeId, status);
		return empUpdateRequestRepository.findByStatusReqBy(employeeId, status, pageable);
	}
}