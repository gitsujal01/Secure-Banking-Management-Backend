package com.mybank.service;

import java.util.List;

import com.mybank.dto.RegisterRequest;
import com.mybank.entity.User;

public interface AdminService {
	
	User createEmployee(RegisterRequest req);
	List<User> getEmployees();
	User updateemployee(Long id,RegisterRequest req);
	void deleteEmployee(Long id);

}
