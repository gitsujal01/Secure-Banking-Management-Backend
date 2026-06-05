package com.mybank.service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mybank.entity.Account;
import com.mybank.entity.Transaction;
import com.mybank.entity.User;
import com.mybank.repository.AccountRepository;
import com.mybank.repository.TransactionRepository;
import com.mybank.repository.UserRepository;

@Service
public class StatementServiceImpl implements StatementService {

    private final UserRepository userrepo;
    private final AccountRepository accountrepo;
    private final TransactionRepository transactionrepo;

    public StatementServiceImpl(UserRepository userrepo,
                                AccountRepository accountrepo,
                                TransactionRepository transactionrepo) {
        this.userrepo = userrepo;
        this.accountrepo = accountrepo;
        this.transactionrepo = transactionrepo;
    }

    @Override
    public byte[] generateStatement(String email) {
        try {
            User user = userrepo.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Account account = accountrepo.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            List<Transaction> transactions =
                    transactionrepo.findBySenderAccountOrReceiverAccount(
                            account.getAccountNumber(),
                            account.getAccountNumber()
                    );

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font normalFont = new Font(Font.HELVETICA, 11);

            document.add(new Paragraph("FinVault Bank Statement", titleFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Customer Name: " + user.getName(), normalFont));
            document.add(new Paragraph("Email: " + user.getEmail(), normalFont));
            document.add(new Paragraph("Account Number: " + account.getAccountNumber(), normalFont));
            document.add(new Paragraph("Account Type: " + account.getAccountType(), normalFont));
            document.add(new Paragraph("Current Balance: Rs. " + account.getBalance(), normalFont));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            table.addCell("ID");
            table.addCell("Type");
            table.addCell("Sender");
            table.addCell("Receiver");
            table.addCell("Amount");
            table.addCell("Date");

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            for (Transaction tx : transactions) {
                table.addCell(String.valueOf(tx.getId()));
                table.addCell(tx.getTransactionType());
                table.addCell(tx.getSenderAccount());
                table.addCell(tx.getReceiverAccount());
                table.addCell("Rs. " + tx.getAmount());
                table.addCell(tx.getTransactionTime().format(formatter));
            }

            document.add(table);
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate statement PDF");
        }
    }
}