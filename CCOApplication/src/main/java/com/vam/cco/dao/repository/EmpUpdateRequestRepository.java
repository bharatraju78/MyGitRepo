package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vam.cco.dao.entity.EmpUpdateRequest;

@Repository
public interface EmpUpdateRequestRepository extends JpaRepository<EmpUpdateRequest, Long> {
	
	
	List<EmpUpdateRequest> findByRequestById(Long requestById);
	List<EmpUpdateRequest> findByRequestToId(Long requestToId);
	
	@Query("SELECT distinct new com.vam.cco.dao.entity.EmpUpdateRequest(e.requestNumber, e.id, e.requestById, e.requestToId, e.employeeId, "
			+ "e.startDate, e.endDate, e.comments, e.status, reqBy.name, reqTo.name, emp.name, e.requestType) "
			+ "FROM EmpUpdateRequest e  "
			+ "JOIN Employee emp ON e.employeeId = emp.employeeId "
			+ "JOIN Employee reqBy ON e.requestById = reqBy.employeeId "
			+ "JOIN Employee reqTo ON e.requestToId = reqTo.employeeId "
			+ "WHERE e.requestToId = ?1 AND e.status = ?2 "
			+ "ORDER BY e.startDate DESC")
	Page<EmpUpdateRequest> findByStatusReqTo(Long employeeId, String status, Pageable pageable);
	
	@Query("SELECT distinct new com.vam.cco.dao.entity.EmpUpdateRequest(e.requestNumber,e.id, e.requestById, e.requestToId, e.employeeId, "
			+ "e.startDate, e.endDate, e.comments, e.status, reqBy.name, reqTo.name, emp.name, e.requestType ) "
			+ "FROM EmpUpdateRequest e  "
			+ "JOIN Employee emp ON e.employeeId = emp.employeeId "
			+ "JOIN Employee reqBy ON e.requestById = reqBy.employeeId "
			+ "JOIN Employee reqTo ON e.requestToId = reqTo.employeeId "
			+ "WHERE e.requestById = ?1 AND e.status = ?2 "
			+ "ORDER BY e.startDate DESC")
	Page<EmpUpdateRequest> findByStatusReqBy(Long employeeId, String status, Pageable pageable);
	
}
