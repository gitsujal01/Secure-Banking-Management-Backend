package com.mybank.dto;

import java.math.BigDecimal;


public class AccountResponse {
	
	private String accountNumber;
	private BigDecimal balance;
	private String accountType;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public AccountResponse(String accountNumber, BigDecimal balance, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
	}
	public AccountResponse() {
		super();
	}
}
