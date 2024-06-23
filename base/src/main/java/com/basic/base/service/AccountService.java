package com.basic.base.service;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.ERole;
import com.basic.base.enums.Gender;
import com.basic.base.model.Account;
import com.basic.base.repository.AccountRepository;
import com.basic.base.repository.impl.CustomAccountRepositoryImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomAccountRepositoryImpl customAccountRepositoryImpl;

    public Account createAccount(String userName, String email, String phoneNumber, Gender gender,
                                 AccountType accountType, ERole roles) {
        Account account = new Account();
        String accountNumber = String.valueOf(customAccountRepositoryImpl.findLastAccountNumber() + 1);
        account.setUserName(userName);
        account.setEmail(email);
        account.setPhoneNumber(phoneNumber);
        account.setAccountNumber(accountNumber);
        account.setGender(gender);
        account.setAccountType(accountType);
        account.setRoles(roles);
        account.setCreatedAt(new Date(System.currentTimeMillis()));
        account.setUpdatedAt(new Date(System.currentTimeMillis()));
        return accountRepository.save(account);
    }

}
