package com.mybank.dto;

import java.math.BigDecimal;

public class TransferRequestDTO {
	private String receiverAccountNumber;
	private BigDecimal amount;

	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
