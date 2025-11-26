package com.vam.cco.controller;

import java.security.Principal;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vam.cco.dao.entity.Portfolio;
import com.vam.cco.services.PortfolioService;
import com.vam.cco.util.IdGenerator;

@Controller
@RequestMapping("/admin/portfolio")
public class PortfolioController {
	
    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    
    private PortfolioService portfolioService;
    private IdGenerator idGenerator;
    public PortfolioController() {}
    
    @Autowired
    public PortfolioController(PortfolioService portfolioService,
    		IdGenerator idGenerator) {
		this.portfolioService = portfolioService;
		this.idGenerator = idGenerator;
	}

    @GetMapping("/list")
    public String listPortfolios(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(required = false) String successMessage,
                                 @RequestParam(required = false) String errorMessage) {
        logger.info("PortfolioController::listPortfolios::start");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Portfolio> portfolioPage = portfolioService.getAllPortfolios(pageable);
        model.addAttribute("portfolios", portfolioPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", portfolioPage.getTotalPages());
        if (successMessage != null) model.addAttribute("successMessage", successMessage);
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        logger.info("PortfolioController::listPortfolios::end");
        return "users/portfolioList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, Principal principal) {
    	logger.info("PortfolioController::showAddForm::start");
        Portfolio portfolio = new Portfolio();
        if (principal != null) {
        	portfolio.setPortfolioNmber(idGenerator.generateAccountId());
            portfolio.setCreatedBy(principal.getName());
            portfolio.setCreatedAt(Calendar.getInstance().getTime());
            portfolio.setUpdatedBy(principal.getName());
            portfolio.setUpdatedAt(Calendar.getInstance().getTime());
        }
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("leaderShipList", portfolioService.getPortfolioLeaders());
        model.addAttribute("hrEmployees", portfolioService.getPortfolioHRs());
        logger.info("PortfolioController::showAddForm::end");
        return "users/portfolioForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
    	logger.info("PortfolioController::showEditForm::start");
        Portfolio portfolio = portfolioService.getPortfolioById(id).orElseThrow(() -> new RuntimeException("Portfolio not found"));
        if(principal != null) {
			portfolio.setUpdatedBy(principal.getName());
			portfolio.setUpdatedAt(Calendar.getInstance().getTime());
		}
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("leaderShipList", portfolioService.getPortfolioLeaders());
        model.addAttribute("hrEmployees", portfolioService.getPortfolioHRs());
        logger.info("PortfolioController::showEditForm::end");
        return "users/portfolioForm";
    }

    @PostMapping("/save")
    public String savePortfolio(@ModelAttribute Portfolio portfolio, Principal principal, Model model) {
    	logger.info("PortfolioController::savePortfolio::start");
    	try {
            if (portfolio.getId() == null) {
                if (principal != null) {
                    portfolio.setCreatedBy(principal.getName());
                    portfolio.setCreatedAt(Calendar.getInstance().getTime());
                }
            }
            if (principal != null) {
                portfolio.setUpdatedBy(principal.getName());
                portfolio.setUpdatedAt(Calendar.getInstance().getTime());
            }
            portfolioService.createPortfolio(portfolio);
            model.addAttribute("successMessage", "Portfolio saved successfully");
        } catch (Exception e) {
            logger.error("Error saving portfolio: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error saving portfolio: " + e.getMessage());
        }
    	logger.info("PortfolioController::savePortfolio::end");
        return "redirect:/admin/portfolio/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePortfolio(@PathVariable Long id) {
    	logger.info("PortfolioController::deletePortfolio::start");
        portfolioService.deletePortfolio(id);
        logger.info("PortfolioController::deletePortfolio::end");
        return "redirect:/admin/portfolio/list";
    }
}