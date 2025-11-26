package com.vam.cco.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vam.cco.dao.entity.Account;
import com.vam.cco.dao.entity.Portfolio;
import com.vam.cco.services.AccountService;
import com.vam.cco.services.PortfolioService;
import com.vam.cco.util.CCOAppliationConstants;

@Controller
@RequestMapping("/admin/portfolio")
public class MapAccountsToPortfolioController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(MapAccountsToPortfolioController.class);
    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private AccountService accountService;

    // GET: Show portfolios and accounts
    @GetMapping("/mapAccounts")
    public String showPortfolios(Model model,
                                @RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                @RequestParam(required = false, name="successMessage") String successMessage,
                                @RequestParam(required = false, name="errorMessage") String errorMessage) {
    	logger.info("MapAccountsToPortfolioController::showPortfolios::start Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Portfolio> portfolioPage = portfolioService.getAllPortfolios(pageable);
        if (null !=portfolioPage && !portfolioPage.isEmpty()) {
        	List<Portfolio> portfolios = portfolioPage.getContent();
        	portfolios.stream().forEach(portfolio -> {
				portfolio.setAccountsModel(accountService.findAccountsByProtfolio(portfolio.getId(), null).getContent());
			});
        	model.addAttribute("portfolios", portfolios);
		}
        List<Account> accounts = accountService.findAll();
        
        model.addAttribute("accounts", accounts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", portfolioPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("portfolio", new Portfolio());
        if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
        if (errorMessage != null) {
        	model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("MapAccountsToPortfolioController::showPortfolios::end");
        return "users/mapAcctToPtflo";
    }

    // POST: Add account to portfolio
    @PostMapping("/addAccount")
    public String addAccountToPortfolio(@ModelAttribute("portfolio") Portfolio portfolioModel,
                                        Model model, Principal principal) {
    	logger.info("MapAccountsToPortfolioController::addAccountToPortfolio::start");
        Portfolio portfolio = portfolioService.getPortfolioById(portfolioModel.getPortfolioId())
				.orElse(null);
        Account account = accountService.findById(portfolioModel.getAccountId()).orElse(null);
        if (portfolio != null && account != null) {
			try {
				account.setPortfolio(portfolio);
				account.setModifiedBy(principal.getName());
				account.setModifiedDate(Calendar.getInstance().getTime());
				account.setAccStartDate(portfolioModel.getStartDate());
				account.setAccEndDate(portfolioModel.getEndDate());
				account.setAccStatus(CCOAppliationConstants.EMP_STATUS_ACTIVE);
				accountService.save(account);
				model.addAttribute("successMessage", "Account added to portfolio successfully.");
			} catch (Exception e) {
				logger.error("Error adding account to portfolio: {}", e.getMessage());
				model.addAttribute("errorMessage", "Error adding account to portfolio: " + e.getMessage());
			}
		} else {
			model.addAttribute("errorMessage", "Portfolio or Account not found.");
		}
        logger.info("MapAccountsToPortfolioController::addAccountToPortfolio::end");
        return "redirect:/admin/portfolio/mapAccounts";
    }
    
    @PostMapping("/editAccount")
    public String editAccountToPortfolio(@ModelAttribute("portfolio") Portfolio portfolioModel,
                                        Model model, Principal principal) {
        Portfolio portfolio = portfolioService.getPortfolioById(portfolioModel.getPortfolioId())
				.orElse(null);
        Account account = accountService.findById(portfolioModel.getAccountId()).orElse(null);
        if (portfolio != null && account != null) {
			try {
				account.setPortfolio(portfolio);
				account.setModifiedBy(principal.getName());
				account.setModifiedDate(Calendar.getInstance().getTime());
				account.setAccStartDate(portfolioModel.getStartDate());
				account.setAccEndDate(portfolioModel.getEndDate());
				account.setAccStatus(CCOAppliationConstants.EMP_STATUS_ACTIVE);
				accountService.save(account);
				model.addAttribute("successMessage", "Account edited successfully.");
			} catch (Exception e) {
				logger.error("Error adding account to portfolio: {}", e.getMessage());
				model.addAttribute("errorMessage", "Error adding account to portfolio: " + e.getMessage());
			}
		} else {
			model.addAttribute("errorMessage", "Portfolio or Account not found.");
		}
        return "redirect:/admin/portfolio/mapAccounts";
    }
}