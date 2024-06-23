package com.basic.base.model;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.ERole;
import com.basic.base.enums.Gender;

import lombok.Data;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;
    private String userName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private AccountType accountType;
    private ERole roles;
    private String accountNumber;
    private double balance;
    private Date createdAt;
    private Date updatedAt;
}
