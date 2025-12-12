package com.vam.cco.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vam.cco.dao.entity.Account;
import com.vam.cco.model.AccountModel;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query(value = "SELECT distinct new com.vam.cco.model.AccountModel(a.accountId, a.accNo, a.accName, a.accCreatedDate,"
			+ "a.accStartDate,a.accEndDate, a.accStatus) FROM Account as a join EmployeeAllocation as ea on a.id = ea.accountId "
			+ "WHERE  "
			+ " ea.employee.employeeId = :employeeId  order by a.accName ")
	Page<AccountModel> findAccountsByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);
	
	@Query(value = "SELECT distinct new com.vam.cco.model.AccountModel(a.accountId, a.accNo, a.accName, a.accCreatedDate,"
			+ "a.accStartDate, a.accEndDate, a.accStatus) FROM Account as a  "
			+ " order by a.accName ")
	Page<AccountModel> findAllActiveAccounts(Pageable pageable);
	
	@Query(value = "SELECT distinct new com.vam.cco.model.AccountModel(a.accountId, a.accNo, a.accName, a.accCreatedDate,"
			+ "a.accStartDate, a.accEndDate, a.accStatus) FROM Account as a "
			+ "where a.portfolio.id = :protfolio and a.accStatus = 'Active' "
			+ " order by a.accName ")
	Page<AccountModel> findAccountsByProtfolio(@Param("protfolio") Long protfolio, Pageable pageable);
	
	
	@Query(value = "SELECT distinct new com.vam.cco.model.AccountModel(a.accountId, a.accNo, a.accName, a.accCreatedDate,"
			+ "a.accStartDate, a.accEndDate, a.accStatus) FROM Account as a "
			+ "where a.portfolio.id in :protfolioIds and a.accStatus = 'Active' "
			+ " order by a.accName ")
	Page<AccountModel> findAccountsByProtfolio(@Param("protfolioIds") List<Long> protfolio, Pageable pageable);
	
	@Query(value = "SELECT distinct new com.vam.cco.model.AccountModel(a.accountId, a.accNo, a.accName, a.accCreatedDate,"
			+ "a.accStartDate, a.accEndDate, a.accStatus) FROM Account as a  "
			+ "where a.accStatus = 'Active'  order by a.accName ")
	List<AccountModel> findAllActiveAccounts();
}
