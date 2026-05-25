package com.mybank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entity.Account;
import com.mybank.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByUser(User user);
}
