package com.mybank.service;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mybank.entity.Account;
import com.mybank.entity.User;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService
{

	private AccountRepository accountrepo;
	private UserRepository userrepo;
	
	
	public AccountServiceImpl(AccountRepository accountrepo, UserRepository userrepo) {
		super();
		this.accountrepo = accountrepo;
		this.userrepo = userrepo;
	}

	@Override
	public Account createAccount(String email) {
		// TODO Auto-generated method stub
		User u = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Account account = new Account();
		String accNo =
		        String.valueOf(
		                1000000000L +
		                new Random().nextInt(900000000)
		        );
		account.setAccountNumber(accNo);
		account.setBalance(BigDecimal.ZERO);
		account.setAccounType("SAVINGS");
		account.setUser(u);
		return accountrepo.save(account);		
	}

	@Override
	public BigDecimal getBalance(String email) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Account account = accountrepo.findByUser(user).orElseThrow(()-> new RuntimeException("Account not found"));
		return account.getBalance();
	}

	@Override
	public void deposit(String email, BigDecimal amount) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Account account = accountrepo.findByUser(user).orElseThrow(()->new RuntimeException("Account not found"));
		account.setBalance(
				account.getBalance().add(amount)
				);
		accountrepo.save(account);
	}
}
