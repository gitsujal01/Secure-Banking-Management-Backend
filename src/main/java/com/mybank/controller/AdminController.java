package com.mybank.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dto.RegisterRequest;
import com.mybank.entity.User;
import com.mybank.service.AdminService;

@RestController
public class AdminController {
	
	private AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@PostMapping("/employee")
	public User createEmployee(@RequestBody RegisterRequest req)
	{
		return adminService.createEmployee(req);
	}
	
	@PutMapping("/employee/{id}")
	public User updateEmployee(@PathVariable Long id,@RequestBody RegisterRequest req)
	{
		return adminService.updateemployee(id, req);
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable Long id)
	{
		adminService.deleteEmployee(id);
		return "Employee deleted successfully";
	}
}
