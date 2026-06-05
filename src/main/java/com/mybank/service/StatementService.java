package com.mybank.service;

public interface StatementService {

	byte[] generateStatement(String email);
}
