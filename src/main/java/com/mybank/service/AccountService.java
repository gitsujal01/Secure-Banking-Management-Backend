package com.mybank.service;

import java.math.BigDecimal;
import java.util.List;

import com.mybank.entity.Account;
import com.mybank.entity.Transaction;

public interface AccountService {

	public Account createAccount(String email);
	public BigDecimal getBalance(String email);
	public void deposit(String email,BigDecimal amount);
	public Account getAccount(String email);
    public void transfer(String senderEmail,String receiverAccount,BigDecimal amount);
    public List<Transaction> getTransaction(String email);
    public void withdraw(String email,BigDecimal amount);
}
