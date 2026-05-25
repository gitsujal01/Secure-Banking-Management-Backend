package com.mybank.service;

import java.math.BigDecimal;

import com.mybank.entity.Account;

public interface AccountService {

	public Account createAccount(String email);
	public BigDecimal getBalance(String email);
	public void deposit(String email,BigDecimal amount);

}
