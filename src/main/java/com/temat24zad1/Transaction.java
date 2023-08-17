package com.temat24zad1;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Transaction {

    private Long id;
    private TransactionType transactionType;
    private String description;
    private double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Transaction() {
    }

    public Transaction(TransactionType transactionType, String description, double amount, LocalDate date) {
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(Long id, TransactionType transactionType, String description, double amount, LocalDate date) {
        this.id = id;
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
