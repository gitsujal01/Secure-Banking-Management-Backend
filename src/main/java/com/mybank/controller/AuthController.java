package com.mybank.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dto.ForgotPasswordDTO;
import com.mybank.dto.LoginRequestDto;
import com.mybank.dto.LoginResponseDto;
import com.mybank.dto.ProfileResponse;
import com.mybank.dto.RegisterRequest;
import com.mybank.dto.RegisterResponse;
import com.mybank.dto.ResetPasswordRequest;
import com.mybank.service.AuthService;
import com.mybank.service.ProfileService;

@RestController
public class AuthController {
	
	private AuthService authservice;

    private ProfileService profileservice;
    
    public AuthController(AuthService authservice, ProfileService profileservice) {
		super();
		this.authservice = authservice;
		this.profileservice = profileservice;
	}

	@PostMapping("/api/auth/register")	
	public RegisterResponse register(@RequestBody RegisterRequest rr)
	{
    	return authservice.createUser(rr);
	}
    
    @PostMapping("/api/auth/login")
    public LoginResponseDto Login(@RequestBody LoginRequestDto loginr)
    {
    	String token = authservice.loginUser(loginr);
    	return new LoginResponseDto(token);
    }
    @GetMapping("/api/auth/profile")
    public ProfileResponse profile()
    {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String email = auth.getName();
    	return profileservice.getProfile(email);
    }
    @PostMapping("/api/auth/forgot-password/send-otp")
    public String sendForgotPasswordOtp(@RequestBody ForgotPasswordDTO req)
    {
    	authservice.sendForgotPasswordOtp(req.getEmail());
    	return "OTP sent successfully";
    }
    @PostMapping("/api/auth/forgot-password/reset")
    public String resetPassword(@RequestBody ResetPasswordRequest req)
    {
    	authservice.resetPassword(req.getEmail(), req.getOtp(), req.getNewPassword());
    	return "Password reset successfully";
    }
}
