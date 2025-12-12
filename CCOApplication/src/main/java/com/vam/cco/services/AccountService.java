package com.vam.cco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vam.cco.dao.entity.Account;
import com.vam.cco.model.AccountModel;

public interface AccountService {
	Account save(Account account);

	Optional<Account> findById(Long id);

	List<Account> findAll();

	Account update(Account account);

	void deleteById(Long id);

	Page<AccountModel> findAllActiveAccounts(Pageable pageable);

	Page<AccountModel> findAccountsByEmployeeId(Long employeeId, Pageable pageable);

	Page<Account> findAll(Pageable pageable);
	
	Page<AccountModel> findAccountsByProtfolio(Long protfolio, Pageable pageable);
	
	Page<AccountModel> findAccountsByProtfolio(List<Long> protfolioIds, Pageable pageable);
	
	List<String> getAllActiveAccountNames();

	List<AccountModel> findAllActiveAccounts();
}