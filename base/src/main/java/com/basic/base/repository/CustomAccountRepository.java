package com.basic.base.repository;

import com.basic.base.model.Account;

public interface CustomAccountRepository {
    Account updateBalanceByAccountNumber(String accountNumber, double newBalance);

    long findLastAccountNumber();
}
