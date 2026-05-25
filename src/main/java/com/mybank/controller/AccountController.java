package com.mybank.controller;

import java.math.BigDecimal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dto.AccountResponse;
import com.mybank.dto.DepositRequest;
import com.mybank.entity.Account;
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
				account.getAccounType()
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
	
}
