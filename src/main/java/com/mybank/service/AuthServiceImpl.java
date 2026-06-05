package com.mybank.service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mybank.dto.LoginRequestDto;
import com.mybank.dto.RegisterRequest;
import com.mybank.dto.RegisterResponse;
import com.mybank.entity.Account;
import com.mybank.entity.OTP;
import com.mybank.entity.User;
import com.mybank.enumm.Role;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.OTPRepository;
import com.mybank.repository.UserRepository;
@Service
public class AuthServiceImpl implements AuthService{

	private PasswordEncoder passwordEncoder;
	private UserRepository userrepo;
    private JWTService jwtservice;
    private AccountRepository accountRepo;  
    private OTPRepository otpRepo;
    private JavaMailSender mailSender;
   

	public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userrepo, JWTService jwtservice,
			AccountRepository accountRepo, OTPRepository otpRepo, JavaMailSender mailSender) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userrepo = userrepo;
		this.jwtservice = jwtservice;
		this.accountRepo = accountRepo;
		this.otpRepo = otpRepo;
		this.mailSender = mailSender;
	}

	@Override
	public RegisterResponse createUser(RegisterRequest rr) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName(rr.getName());
		user.setEmail(rr.getEmail());
		user.setPassword(passwordEncoder.encode(rr.getPassword()));
		user.setRole(Role.Customer);
		User savedUser = userrepo.save(user);
	    Account account = new Account();
	    account.setUser(savedUser);
	    account.setAccountType("SAVINGS");
	    account.setBalance(BigDecimal.ZERO);
	    String AccountNumber = String.valueOf(1000000000L+ new Random().nextInt(900000000));
	    account.setAccountNumber(AccountNumber);
	    accountRepo.save(account);
		return new RegisterResponse(
				savedUser.getId(),
				savedUser.getName(),
				savedUser.getEmail()
	    );
	}

	@Override
	public String loginUser(LoginRequestDto loginreq) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(loginreq.getEmail()).orElseThrow(()->
		            new RuntimeException("User not found"));
		boolean ismatch = passwordEncoder.matches(loginreq.getPassword(),
				user.getPassword());
		if(!ismatch)
		{
			throw new RuntimeException("Invalid Password");
		}
		
     	return jwtservice.generateToken(user.getEmail());
	}

	@Override
	public void sendForgotPasswordOtp(String email) {
		// TODO Auto-generated method stub
         userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
         otpRepo.deleteByEmail(email);
         String otp = String.valueOf(100000+new Random().nextInt(900000));
         OTP otpEntity = new OTP();
         otpEntity.setEmail(email);
         otpEntity.setOtp(otp);
         otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
         otpRepo.save(otpEntity);
         SimpleMailMessage message = new SimpleMailMessage();
         message.setTo(email);
         message.setSubject("Password Reset Otp");
         message.setText("Your Password reset OTP is : "+otp);
         mailSender.send(message);
	}

	@Override
	public void resetPassword(String email, String otp, String newPassword) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		OTP otpEntity = otpRepo.findByEmail(email).orElseThrow(()->new RuntimeException("OTP not found"));
		if(LocalDateTime.now().isAfter(otpEntity.getExpiryTime()))
		{
			throw new RuntimeException("OTP Expired");
		}
		if(!otpEntity.getOtp().equals(otp))
		{
			throw new RuntimeException("Invalid Otp");
		}
		user.setPassword(passwordEncoder.encode(newPassword));
		userrepo.save(user);
		otpRepo.deleteByEmail(email);
	}
}
