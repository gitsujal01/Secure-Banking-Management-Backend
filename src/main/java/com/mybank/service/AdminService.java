package com.mybank.service;

import java.util.List;

import com.mybank.dto.AdminDashboardResponse;
import com.mybank.dto.RegisterRequest;
import com.mybank.entity.Transaction;
import com.mybank.entity.User;

public interface AdminService {

    User createEmployee(RegisterRequest req);
    List<User> getEmployees();
    User updateemployee(Long id, RegisterRequest req);
    void deleteEmployee(Long id);
    List<User> getAllUsers();
    List<Transaction> getAllTransactions();
    AdminDashboardResponse getDashboardResponse();
}