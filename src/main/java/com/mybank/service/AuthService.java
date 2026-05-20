package com.mybank.service;

import com.mybank.dto.LoginRequestDto;
import com.mybank.dto.RegisterRequest;
import com.mybank.dto.RegisterResponse;

public interface AuthService {
	
	public RegisterResponse createUser(RegisterRequest rr);
	public String loginUser(LoginRequestDto loginreq);
}
