package com.mybank.service;

import java.math.BigDecimal;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendTransferSuccessToSender(
            String senderEmail,
            String receiverAccountNumber,
            BigDecimal amount,
            BigDecimal remainingBalance) {

        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, false, "UTF-8");

            helper.setFrom(
                    new InternetAddress(
                            "sujallokhande23@gmail.com",
                            "FinVault Bank"
                    )
            );

            helper.setTo(senderEmail);
            helper.setSubject("Transfer Successful - FinVault Bank");

            helper.setText(
                    "Dear Customer,\n\n" +
                    "Your transfer was successful.\n\n" +
                    "Amount: Rs. " + amount + "\n" +
                    "Receiver Account: " + receiverAccountNumber + "\n" +
                    "Remaining Balance: Rs. " + remainingBalance + "\n\n" +
                    "Regards,\n" +
                    "FinVault Bank",
                    false
            );

            mailSender.send(message);

        } catch (Exception e) {
            System.out.println("Sender email failed: " + e.getMessage());
        }
    }

    @Override
    public void sendMoneyReceivedToReceiver(
            String receiverEmail,
            String senderAccountNumber,
            BigDecimal amount,
            BigDecimal availableBalance) {

        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, false, "UTF-8");

            helper.setFrom(
                    new InternetAddress(
                            "sujallokhande23@gmail.com",
                            "FinVault Bank"
                    )
            );

            helper.setTo(receiverEmail);
            helper.setSubject("Money Received - FinVault Bank");

            helper.setText(
                    "Dear Customer,\n\n" +
                    "Rs. " + amount + " has been credited to your account.\n\n" +
                    "Sender Account: " + senderAccountNumber + "\n" +
                    "Available Balance: Rs. " + availableBalance + "\n\n" +
                    "Regards,\n" +
                    "FinVault Bank",
                    false
            );

            mailSender.send(message);

        } catch (Exception e) {
            System.out.println("Receiver email failed: " + e.getMessage());
        }
    }
}