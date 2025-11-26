package com.vam.cco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vam.cco.dao.entity.Portfolio;
import com.vam.cco.model.EmployeeModel;

public interface PortfolioService {
	Portfolio createPortfolio(Portfolio portfolio);

	Portfolio updatePortfolio(Long id, Portfolio portfolio);

	void deletePortfolio(Long id);

	Optional<Portfolio> getPortfolioById(Long id);

	Page<Portfolio> getAllPortfolios(Pageable pageable);
	
	List<EmployeeModel> getPortfolioLeaders();
	
	List<EmployeeModel> getPortfolioHRs();
	
	List<Long> getPortfolioIdsByEmployeeId(Long employeeId);
	
	Portfolio getPortfolioByEmployeeId(Long id);
}
