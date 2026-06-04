package com.mybank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entity.User;
import com.mybank.enumm.Role;


public interface UserRepository extends JpaRepository<User, Long>{

	 Optional<User> findByEmail(String email);
	 List<User> findByRole(Role role);
}
