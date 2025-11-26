package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vam.cco.dao.entity.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

	@Query("SELECT distinct new com.vam.cco.dao.entity.Portfolio( p.id, p.portfolioNmber, p.name, p.active, p.portfolioLeader, e.name, "
			+ "p.portfolioHR, e1.name, p.description) FROM Portfolio p "
			+ "Join Employee e ON p.portfolioLeader = e.id "
			+ "Join Employee e1 ON p.portfolioHR = e1.id "
			+ "order by p.name")
	Page<Portfolio> findAllPortfolios(Pageable pageable);

	@Query("SELECT p FROM Portfolio p Join p.accounts WHERE p.active = true")
	Page<Portfolio> findActivePortfolios(Pageable pageable);
	
	@Query("SELECT p.id FROM Portfolio p WHERE p.portfolioLeader = :employeeId OR p.portfolioHR = :employeeId")
	List<Long> findPortfolioIdsByEmployeeId(Long employeeId);
	
	@Query("SELECT p FROM Portfolio p WHERE p.portfolioLeader = :employeeId OR p.portfolioHR = :employeeId")
	Portfolio findPortfolioByEmployeeId(Long employeeId);
	
	
}