package com.mybank.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mybank.dto.AdminDashboardResponse;
import com.mybank.dto.RegisterRequest;
import com.mybank.entity.Transaction;
import com.mybank.entity.User;
import com.mybank.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public AdminDashboardResponse dashboard() {
        return adminService.getDashboardResponse();
    }

    @PostMapping("/employee")
    public User createEmployee(@RequestBody RegisterRequest req) {
        return adminService.createEmployee(req);
    }

    @GetMapping("/employees")
    public List<User> getEmployees() {
        return adminService.getEmployees();
    }

    @PutMapping("/employee/{id}")
    public User updateEmployee(@PathVariable Long id,
                               @RequestBody RegisterRequest req) {
        return adminService.updateemployee(id, req);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        adminService.deleteEmployee(id);
        return "Employee deleted successfully";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return adminService.getAllTransactions();
    }
}