package com.mybank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mybank.dto.AdminDashboardResponse;
import com.mybank.dto.RegisterRequest;
import com.mybank.entity.Account;
import com.mybank.entity.Transaction;
import com.mybank.entity.User;
import com.mybank.enumm.Role;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.TransactionRepository;
import com.mybank.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userrepo;
    private final PasswordEncoder passwordEncoder;
    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public AdminServiceImpl(UserRepository userrepo,
                            PasswordEncoder passwordEncoder,
                            TransactionRepository transactionRepo,
                            AccountRepository accountRepo) {
        this.userrepo = userrepo;
        this.passwordEncoder = passwordEncoder;
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public User createEmployee(RegisterRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.Employee);

        return userrepo.save(user);
    }

    @Override
    public List<User> getEmployees() {
        return userrepo.findByRole(Role.Employee);
    }

    @Override
    public User updateemployee(Long id, RegisterRequest req) {
        User user = userrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (user.getRole() != Role.Employee) {
            throw new RuntimeException("Only Employee can be updated");
        }

        user.setName(req.getName());
        user.setEmail(req.getEmail());

        if (req.getPassword() != null && !req.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }

        return userrepo.save(user);
    }

    @Override
    public void deleteEmployee(Long id) {
        User user = userrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (user.getRole() != Role.Employee) {
            throw new RuntimeException("Only employee can be deleted");
        }

        userrepo.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userrepo.findByRole(Role.Customer);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    @Override
    public AdminDashboardResponse getDashboardResponse() {
        List<Transaction> transactions = transactionRepo.findAll();

        long totalCustomers = userrepo.findByRole(Role.Customer).size();
        long totalEmployees = userrepo.findByRole(Role.Employee).size();
        long totalTransactions = transactions.size();
        long totalAccounts = accountRepo.count();

        BigDecimal totalBalance = accountRepo.findAll()
                .stream()
                .map(Account::getBalance)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDeposits = transactions.stream()
                .filter(t -> "deposit".equalsIgnoreCase(t.getTransactionType()))
                .map(Transaction::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalWithdrawals = transactions.stream()
                .filter(t -> "withdraw".equalsIgnoreCase(t.getTransactionType()))
                .map(Transaction::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalTransfers = transactions.stream()
                .filter(t -> "transfer".equalsIgnoreCase(t.getTransactionType()))
                .map(Transaction::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AdminDashboardResponse(
                totalCustomers,
                totalEmployees,
                totalTransactions,
                totalAccounts,
                totalBalance,
                totalDeposits,
                totalWithdrawals,
                totalTransfers
        );
    }
}