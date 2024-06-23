package com.basic.base.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.basic.base.model.Account;
import com.basic.base.repository.CustomAccountRepository;

@Repository
public class CustomAccountRepositoryImpl implements CustomAccountRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Account updateBalanceByAccountNumber(String accountNumber, double newBalance) {
        Query query = new Query(Criteria.where("accountNumber").is(accountNumber));
        Update update = new Update().set("balance", newBalance);
        return mongoTemplate.findAndModify(query, update, Account.class);
    }

    @Override
    public long findLastAccountNumber() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "accountNumber"));
        query.limit(1);
        Account lastAccount = mongoTemplate.findOne(query, Account.class);
        return lastAccount != null ? Long.parseLong(lastAccount.getAccountNumber()) : 0;
    }

}
