package com.mybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dto.LoginRequestDto;
import com.mybank.dto.RegisterRequest;
import com.mybank.dto.RegisterResponse;
import com.mybank.service.AuthService;

@RestController
public class AuthController {
	
	private AuthService authservice;

	public AuthController(AuthService authservice) {
		super();
		this.authservice = authservice;
	}

    @PostMapping("/api/auth/register")	
	public RegisterResponse register(@RequestBody RegisterRequest rr)
	{
    	return authservice.createUser(rr);
	}
    
    @PostMapping("/api/auth/login")
    public String Login(@RequestBody LoginRequestDto loginr)
    {
    	return authservice.loginUser(loginr);
    }
    @GetMapping("/api/auth/profile")
    public String profile()
    {
    	return "Protected api working";
    }
}
