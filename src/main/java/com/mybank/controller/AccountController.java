package com.mybank.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dto.AccountResponse;
import com.mybank.dto.DepositRequest;
import com.mybank.dto.TransferRequestDTO;
import com.mybank.entity.Account;
import com.mybank.entity.Transaction;
import com.mybank.service.AccountService;

@RestController
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	@PostMapping("/create")
	public AccountResponse createAccount()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
        Account account = accountService.createAccount(email);
		return new AccountResponse(
				account.getAccountNumber(),
				account.getBalance(),
				account.getAccountType()
				);
	}
	
	@GetMapping("/balance")
	public BigDecimal getBalance()
	{
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		String email = authenticate.getName();
		return accountService.getBalance(email);
	}
	@PostMapping("/deposit")
    public String deposit(@RequestBody DepositRequest deporeq)
    {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String email = auth.getName();
    	accountService.deposit(email, deporeq.getAmount());
    	return "Money Deposited Successfully";
    }
	@GetMapping("/api/account")
	public AccountResponse getAccount() {

	    Authentication auth =
	            SecurityContextHolder.getContext().getAuthentication();

	    String email = auth.getName();

	    Account account = accountService.getAccount(email);

	    return new AccountResponse(
	            account.getAccountNumber(),
	            account.getBalance(),
	            account.getAccountType()
	    );
	}
	@PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequestDTO req)
    {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String email = auth.getName();
    	accountService.transfer(email, req.getReceiverAccountNumber(),req.getAmount());
    
    	return "Money Transferred Successfully";
    
    }
	@GetMapping("/transaction")
	public List<Transaction> getTransaction()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return accountService.getTransaction(email);
	}
}
