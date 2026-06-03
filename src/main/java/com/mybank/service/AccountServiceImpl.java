package com.mybank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mybank.entity.Account;
import com.mybank.entity.Transaction;
import com.mybank.entity.User;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.TransactionRepository;
import com.mybank.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService
{

	private AccountRepository accountrepo;
	private UserRepository userrepo;
	private TransactionRepository transactionrepo;
	
    
	public AccountServiceImpl(AccountRepository accountrepo, UserRepository userrepo,
			TransactionRepository transactionrepo) {
		super();
		this.accountrepo = accountrepo;
		this.userrepo = userrepo;
		this.transactionrepo = transactionrepo;
	}

	@Override
	public Account createAccount(String email) {
		// TODO Auto-generated method stub
		User u = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Account account = new Account();
		String accNo =
		        String.valueOf(
		                1000000000L +
		                new Random().nextInt(900000000)
		        );
		account.setAccountNumber(accNo);
		account.setBalance(BigDecimal.ZERO);
		account.setAccountType("SAVINGS");
		account.setUser(u);
		return accountrepo.save(account);		
	}

	@Override
	public BigDecimal getBalance(String email) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Account account = accountrepo.findByUser(user).orElseThrow(()-> new RuntimeException("Account not found"));
		return account.getBalance();
	}

	@Override
	public void deposit(String email, BigDecimal amount) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Account account = accountrepo.findByUser(user).orElseThrow(()->new RuntimeException("Account not found"));
		account.setBalance(
				account.getBalance().add(amount)
				);
		accountrepo.save(account);
	}
	@Override
	public Account getAccount(String email) {

	    User user = userrepo.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    return accountrepo.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Account not found"));
	}

	@Override
	public void transfer(String senderEmail,
	                     String receiverAccount,
	                     BigDecimal amount) {

	    User senderUser = userrepo.findByEmail(senderEmail)
	            .orElseThrow(() ->
	                    new RuntimeException("Sender not found"));

	    Account senderaccount = accountrepo.findByUser(senderUser)
	            .orElseThrow(() ->
	                    new RuntimeException("Sender Account not found"));

	    // receiver account
	    System.out.println(receiverAccount);

	    Account receiver = accountrepo
	            .findByAccountNumber(receiverAccount)
	            .orElseThrow(() ->
	                    new RuntimeException("Receiver not found"));

	    if(senderaccount.getBalance()
	            .compareTo(amount) < 0)
	    {
	        throw new RuntimeException("Insufficient Balance");
	    }

	    // deduct sender balance
	    senderaccount.setBalance(
	            senderaccount.getBalance()
	                    .subtract(amount)
	    );

	    // add receiver balance
	    receiver.setBalance(
	            receiver.getBalance()
	                    .add(amount)
	    );

	    accountrepo.save(senderaccount);

	    accountrepo.save(receiver);


	    Transaction tx = new Transaction();

	    tx.setSenderAccount(
	            senderaccount.getAccountNumber()
	    );

	    tx.setReceiverAccount(
	            receiver.getAccountNumber()
	    );

	    tx.setAmount(amount);
        tx.setTransactionType("transfer");
	    tx.setTransactionTime(
	            LocalDateTime.now()
	    );

	    transactionrepo.save(tx);
	}

	@Override
	public List<Transaction> getTransaction(String email) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Account account = accountrepo.findByUser(user).orElseThrow(()->new RuntimeException("Account not found"));
		
		return transactionrepo.findBySenderAccountOrReceiverAccount(account.getAccountNumber(), account.getAccountNumber());
	}

	@Override
	public void withdraw(String email, BigDecimal amount) {
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
		Account account = accountrepo.findByUser(user).orElseThrow(()->new RuntimeException("Account not found"));
		if(account.getBalance().compareTo(amount)<0)
		{
			throw new RuntimeException("Insufficient Balance");
		}
		account.setBalance(account.getBalance().subtract(amount));
		accountrepo.save(account);
	}
}
