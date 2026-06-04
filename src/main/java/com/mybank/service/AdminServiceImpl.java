package com.mybank.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mybank.dto.RegisterRequest;
import com.mybank.entity.User;
import com.mybank.enumm.Role;
import com.mybank.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService
{

	private UserRepository userrepo;
	private PasswordEncoder passwordEncoder;
	
	public AdminServiceImpl(UserRepository userrepo, PasswordEncoder passwordEncoder) {
		super();
		this.userrepo = userrepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User createEmployee(RegisterRequest req) {
		// TODO Auto-generated method stub
	    User user = new User();
	    user.setName(req.getName());
	    user.setEmail(req.getEmail());
	    user.setPassword(passwordEncoder.encode(req.getPassword()));
	    user.setRole(Role.Employee);
		return userrepo.save(user);
	}

	@Override
	public List<User> getEmployees() {
		// TODO Auto-generated method stub
		return userrepo.findByRole(Role.Employee);
	}

	@Override
	public User updateemployee(Long id, RegisterRequest req) {
		// TODO Auto-generated method stub
		User user = userrepo.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
		if(user.getRole()!=Role.Employee) {
			throw new RuntimeException("Only Employee can be updated");
		}
		user.setName(req.getName());
		user.setEmail(req.getEmail());
		return userrepo.save(user);
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		User user = userrepo.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
		if(user.getRole()!=Role.Employee)
		{
			throw new RuntimeException("Only employee can be deleted");
		}
		userrepo.delete(user);
	}

}
