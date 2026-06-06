package com.mybank.service;

import org.springframework.stereotype.Service;

import com.mybank.dto.ProfileResponse;
import com.mybank.entity.Account;
import com.mybank.entity.User;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepo;
    private final AccountRepository accountRepo;

    public ProfileServiceImpl(UserRepository userRepo,
                              AccountRepository accountRepo) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public ProfileResponse getProfile(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = accountRepo.findByUser(user)
                .orElse(null);

        return new ProfileResponse(
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                account != null ? account.getAccountNumber() : null,
                account != null ? account.getBalance() : null
        );
    }
}