package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.Account;
import com.vam.cco.dao.repository.AccountRepository;
import com.vam.cco.model.AccountModel;
import com.vam.cco.services.AccountService;
import com.vam.cco.util.StatusEnum;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        logger.info("Saving account: {}", account);
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        logger.info("Finding account by id: {}", id);
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findAll() {
        logger.info("Finding all accounts");
        return accountRepository.findAll();
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        logger.info("Finding all accounts with pagination: {}", pageable);
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account update(Account account) {
        logger.info("Updating account: {}", account);
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting account by id: {}", id);
        accountRepository.deleteById(id);
    }

    @Override
    public Page<AccountModel> findAllActiveAccounts(Pageable pageable) {
        logger.info("Finding all active accounts");
        return accountRepository.findAllActiveAccounts(pageable);
    }

    @Override
    public Page<AccountModel> findAccountsByEmployeeId(Long employeeId, Pageable pageable) {
        logger.info("Finding accounts by employee id: {}", employeeId);
        return accountRepository.findAccountsByEmployeeId(employeeId, pageable);
    }
    
    @Override
    public Page<AccountModel> findAccountsByProtfolio(Long protfolio, Pageable pageable) {
    	return accountRepository.findAccountsByProtfolio(protfolio, pageable);
    }
    
    @Override
    public Page<AccountModel> findAccountsByProtfolio(List<Long> protfolioIds, Pageable pageable) {
    	return accountRepository.findAccountsByProtfolio(protfolioIds, pageable);
    }
    
    @Override
    public List<AccountModel> findAllActiveAccounts() {
        logger.info("Finding all active accounts");
        return accountRepository.findAllActiveAccounts();
    }

	@Override
	public List<String> getAllActiveAccountNames() {
//	    List<Account> accounts = findAll();
		List<AccountModel> accounts = accountRepository.findAllActiveAccounts();
	    if (accounts != null && !accounts.isEmpty()) {
//	        return accounts.stream()
//	                .filter(a -> a.getAccStatus().equals(StatusEnum.ACTIVE.getStatus()))   // Filter only active accounts
//	                .map(a -> a.getAccName())
//	                .toList();
	    	return accounts.stream().map(a -> a.getAccName()).toList();
	    }
	    return List.of();   // Return empty list instead of null (best practice)
	}
}