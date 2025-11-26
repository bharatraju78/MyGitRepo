package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.Portfolio;
import com.vam.cco.dao.repository.EmployeeRepository;
import com.vam.cco.dao.repository.PortfolioRepository;
import com.vam.cco.model.EmployeeModel;
import com.vam.cco.services.PortfolioService;
import com.vam.cco.util.CCOAppliationConstants;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioServiceImpl.class);

    private PortfolioRepository portfolioRepository;
    private EmployeeRepository employeeRepository;

    public PortfolioServiceImpl() {
    }

    @Autowired
    PortfolioServiceImpl(PortfolioRepository portfolioRepository,
            EmployeeRepository employeeRepository) {
        this.portfolioRepository = portfolioRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Portfolio createPortfolio(Portfolio portfolio) {
        logger.info("Creating portfolio: {}", portfolio);
        return portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio updatePortfolio(Long id, Portfolio portfolio) {
        logger.info("Updating portfolio with id: {}", id);
        if (!portfolioRepository.existsById(id)) {
            logger.warn("Portfolio not found with id: {}", id);
            throw new RuntimeException("Portfolio not found with id: " + id);
        }
        portfolio.setId(id);
        return portfolioRepository.save(portfolio);
    }

    @Override
    public void deletePortfolio(Long id) {
        logger.info("Deleting portfolio with id: {}", id);
        portfolioRepository.deleteById(id);
    }

    @Override
    public Optional<Portfolio> getPortfolioById(Long id) {
        logger.info("Fetching portfolio by id: {}", id);
        return portfolioRepository.findById(id);
    }

    @Override
    public Page<Portfolio> getAllPortfolios(Pageable pageable) {
        logger.info("Fetching all portfolios");
        return portfolioRepository.findAllPortfolios(pageable);
    }

    @Override
    public List<EmployeeModel> getPortfolioHRs() {
        logger.info("Fetching portfolio HRs start");
        List<String> hrDesignations = List.of("HR", "HR Manager", "HR Executive");
        logger.info("Fetching portfolio hrDesignations = {}", hrDesignations);
        List<EmployeeModel> hrEmployees = employeeRepository.findByDesignationNamesForPortfolio(hrDesignations);
        logger.info("Found {} HR employees for portfolios", hrEmployees.size());
        logger.info("Fetching portfolio HRs end");
        return hrEmployees;
    }

    @Override
    public List<EmployeeModel> getPortfolioLeaders() {
        logger.info("Fetching portfolio leaders start");
        logger.info("Fetching portfolio leaders designations = {}", CCOAppliationConstants.leaderShipList);
        List<EmployeeModel> leaders = employeeRepository.findByDesignationNamesForPortfolio(CCOAppliationConstants.leaderShipList);
        logger.info("Fetching portfolio leaders size = {}", leaders.size());
        logger.info("Fetching portfolio leaders end");
        return leaders;
    }
    
   @Override
   public List<Long> getPortfolioIdsByEmployeeId(Long employeeId) {
	   logger.info("PortfolioServiceImpl:: getPortfolioIdsByEmployeeId start for employeeId: {}", employeeId);
	   List<Long> portfolioIds = portfolioRepository.findPortfolioIdsByEmployeeId(employeeId);
	   logger.info("PortfolioServiceImpl:: getPortfolioIdsByEmployeeId portfolioIds = {}", portfolioIds);
	   logger.info("PortfolioServiceImpl:: getPortfolioIdsByEmployeeId end");
	   return portfolioIds;
   }
   
   @Override
   public Portfolio getPortfolioByEmployeeId(Long id) {
	   logger.info("PortfolioServiceImpl:: getPortfolioIdsByEmployeeId start");
	   Portfolio portfolio = portfolioRepository.findPortfolioByEmployeeId(id);
	   logger.info("PortfolioServiceImpl:: getPortfolioIdsByEmployeeId portfolio = {}", portfolio);
	   logger.info("PortfolioServiceImpl:: getPortfolioIdsByEmployeeId end");
	   return portfolio;
   }
    
}