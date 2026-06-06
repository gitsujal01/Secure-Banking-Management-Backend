package com.mybank.dto;

import java.math.BigDecimal;

public class ProfileResponse {

    private String name;
    private String email;
    private String role;
    private String accountNumber;
    private BigDecimal balance;

    public ProfileResponse(String name,
                           String email,
                           String role,
                           String accountNumber,
                           BigDecimal balance) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}