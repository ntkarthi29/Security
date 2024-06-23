package com.basic.base.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.basic.base.enums.TransactionType;

import lombok.Data;

@Data
@Document(collection = "transaction")
public class Transaction {
    @Id
    private String id;
    private String accountNumber;
    private double amount;
    private double balanceAfter;
    private Date createdAt;
    private TransactionType transactionType;
}
