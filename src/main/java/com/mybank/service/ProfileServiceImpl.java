package com.mybank.service;

import org.springframework.stereotype.Service;

import com.mybank.dto.ProfileResponse;
import com.mybank.entity.Account;
import com.mybank.entity.User;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService
{

	private UserRepository userrepo;
	
	private AccountRepository accountRepo;
	
	
	
	public ProfileServiceImpl(UserRepository userrepo, AccountRepository accountRepo) {
		super();
		this.userrepo = userrepo;
		this.accountRepo = accountRepo;
	}
	@Override
	public ProfileResponse getProfile(String email) {
		// TODO Auto-generated method stub
		System.out.println(email);
	    User user = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		System.out.println(user.getEmail());
	    Account account = accountRepo.findByUser(user).orElseThrow(()->new RuntimeException("Account not found"));
		return new ProfileResponse(
				user.getName(),
				user.getEmail(),
				user.getRole(),
				account.getAccountNumber(),
				account.getAccountType()
				);
	}

}
