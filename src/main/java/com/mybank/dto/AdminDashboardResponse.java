package com.mybank.dto;

import java.math.BigDecimal;

public class AdminDashboardResponse {

    private long totalCustomers;
    private long totalEmployees;
    private long totalTransactions;
    private long totalAccounts;
    private BigDecimal totalBalance;
    private BigDecimal totalDeposits;
    private BigDecimal totalWithDrawls;
    private BigDecimal totalTransfers;
	public AdminDashboardResponse(long totalCustomers, long totalEmployees, long totalTransactions, long totalAccounts,
			BigDecimal totalBalance, BigDecimal totalDeposits, BigDecimal totalWithDrawls, BigDecimal totalTransfers) {
		super();
		this.totalCustomers = totalCustomers;
		this.totalEmployees = totalEmployees;
		this.totalTransactions = totalTransactions;
		this.totalAccounts = totalAccounts;
		this.totalBalance = totalBalance;
		this.totalDeposits = totalDeposits;
		this.totalWithDrawls = totalWithDrawls;
		this.totalTransfers = totalTransfers;
	}
	public long getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}
	public long getTotalEmployees() {
		return totalEmployees;
	}
	public void setTotalEmployees(long totalEmployees) {
		this.totalEmployees = totalEmployees;
	}
	public long getTotalTransactions() {
		return totalTransactions;
	}
	public void setTotalTransactions(long totalTransactions) {
		this.totalTransactions = totalTransactions;
	}
	public long getTotalAccounts() {
		return totalAccounts;
	}
	public void setTotalAccounts(long totalAccounts) {
		this.totalAccounts = totalAccounts;
	}
	public BigDecimal getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}
	public BigDecimal getTotalDeposits() {
		return totalDeposits;
	}
	public void setTotalDeposits(BigDecimal totalDeposits) {
		this.totalDeposits = totalDeposits;
	}
	public BigDecimal getTotalWithDrawls() {
		return totalWithDrawls;
	}
	public void setTotalWithDrawls(BigDecimal totalWithDrawls) {
		this.totalWithDrawls = totalWithDrawls;
	}
	public BigDecimal getTotalTransfers() {
		return totalTransfers;
	}
	public void setTotalTransfers(BigDecimal totalTransfers) {
		this.totalTransfers = totalTransfers;
	}
    
}