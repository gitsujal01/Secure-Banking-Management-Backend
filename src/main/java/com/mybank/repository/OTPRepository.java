package com.mybank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entity.OTP;

import jakarta.transaction.Transactional;

public interface OTPRepository extends JpaRepository<OTP, Long>{
	
	Optional<OTP> findByEmail(String email);
	@Transactional
    void deleteByEmail(String email);
	
}
