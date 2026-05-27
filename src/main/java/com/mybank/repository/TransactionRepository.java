package com.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findBySenderAccountOrReceiverAccount(String sender,String receiver);

}
