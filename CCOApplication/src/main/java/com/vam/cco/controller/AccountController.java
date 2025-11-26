package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vam.cco.dao.entity.Account;
import com.vam.cco.model.AccountModel;
import com.vam.cco.services.AccountService;
import com.vam.cco.util.IdGenerator;
import com.vam.cco.util.StatusEnum;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
	
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    
    private final AccountService accountService;
    
    private final IdGenerator idGenerator;

    @Autowired
    public AccountController(AccountService accountService, IdGenerator idGenerator) {
        this.accountService = accountService;
        this.idGenerator = idGenerator;
    }

    @GetMapping("/account-list")
    public String listAccounts(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false, name="successMessage") String successMessage,
                              @RequestParam(required = false, name="errorMessage") String errorMessage) {
        logger.info("Fetching accounts with pagination. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Account> accountPage = accountService.findAll(pageable);
        model.addAttribute("accounts", accountPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", accountPage.getTotalPages());
        if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
		}
        if (errorMessage != null) {
        	model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("Total accounts found: {}", accountPage.getTotalElements());
        return "users/accountList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add account form");
        String accountNumber = idGenerator.generateAccountId();
		AccountModel accountModel = new AccountModel();
		accountModel.setAccNo(accountNumber);
		accountModel.setAccStatusList(StatusEnum.getAllStatus());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			logger.info("AccountController::generateAccountNumber::Authentication found: {}", authentication.getName());
			accountModel.setCreatedBy(authentication.getName());
			accountModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			accountModel.setModifiedBy(authentication.getName());
			accountModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		} else {
			logger.info("AccountController::generateAccountNumber::No authentication found");
		}
        model.addAttribute("account", accountModel);
        return "users/accountForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for account id: {}", id);
        Account account = accountService.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        AccountModel accountModel = new AccountModel();
		accountModel.setAccNo(account.getAccNo());
		accountModel.setAccountId(account.getAccountId());
		accountModel.setAccName(account.getAccName());
		accountModel.setAccStatus(account.getAccStatus());
		accountModel.setCreatedBy(account.getCreatedBy());
		if(null != account.getCreatedDate()) {
			accountModel.setCreatedDate(account.getCreatedDate());
		} else {
			accountModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			if(null == accountModel.getCreatedBy()) {
				accountModel.setCreatedBy(authentication.getName());
			}
			if(null == accountModel.getModifiedBy()) {
				accountModel.setModifiedBy(authentication.getName());
			}
		}
		if(null != accountModel.getModifiedDate()) {
			accountModel.setModifiedDate(account.getModifiedDate());
		} else {
			accountModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		accountModel.setAccStatusList(StatusEnum.getAllStatus());
		logger.info("Showing edit form for account accountModel: {}", accountModel);
        model.addAttribute("account", accountModel);
        return "users/accountForm";
    }

    @PostMapping("/account-save")
    public String saveAccount(@ModelAttribute AccountModel accountModel, Model model) {
        logger.info("Saving account: {}", accountModel.getAccName());
        try {
			Account account = new Account();
			Long accountId = accountModel.getAccountId();
			account.setAccountId(accountModel.getAccountId());
			account.setAccNo(accountModel.getAccNo());
			account.setAccName(accountModel.getAccName());
			account.setAccStatus(accountModel.getAccStatus());
			account.setCreatedBy(accountModel.getCreatedBy());
			account.setCreatedDate(accountModel.getCreatedDate());
			account.setModifiedBy(accountModel.getModifiedBy());
			account.setModifiedDate(accountModel.getModifiedDate());
			accountService.save(account);
			if(null == accountId) {
				model.addAttribute("successMessage", "Account saved successfully");
			} else {
				model.addAttribute("successMessage", "Account updated successfully");
			}
			
		} catch (Exception e) {
			logger.error("Error saving account: {}", e.getMessage());
			model.addAttribute("errorMessage", "Error creating Account:"+ e.getMessage());
		}
        
        logger.info("Account saved successfully");
        return "redirect:/admin/account/account-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        logger.info("Deleting account with id: {}", id);
        accountService.deleteById(id);
        logger.info("Account deleted successfully");
        return "redirect:/admin/account/account-list";
    }
}