package com.mybank.dto;

import com.mybank.enumm.Role;

public class ProfileResponse {
	
	private String name;
	private String email;
	private Role role;
	private String accountNumber;
	private String accountType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public ProfileResponse(String name, String email, Role role, String accountNumber, String accountType) {
		super();
		this.name = name;
		this.email = email;
		this.role = role;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
	}
    
}
