package com.mybank.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mybank.dto.LoginRequestDto;
import com.mybank.dto.RegisterRequest;
import com.mybank.dto.RegisterResponse;
import com.mybank.entity.User;
import com.mybank.enumm.Role;
import com.mybank.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	private PasswordEncoder passwordEncoder;
	private UserRepository userrepo;
    private JWTService jwtservice;
    

    


	public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userrepo, JWTService jwtservice) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userrepo = userrepo;
		this.jwtservice = jwtservice;
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
}
