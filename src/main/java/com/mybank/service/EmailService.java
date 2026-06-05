package com.mybank.service;

import java.math.BigDecimal;

public interface EmailService {

	void sendTransferSuccessToSender(String senderEmail,String receiverAccountNumber,BigDecimal amount,BigDecimal remainingBalance);
    void sendMoneyReceivedToReceiver(String receiverEmail,String senderAccountNumber,BigDecimal amount,BigDecimal availableBalance);
}
