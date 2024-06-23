package com.basic.base.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.basic.base.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>{

    @Query(value = "{ 'accountNumber': ?0 }", sort = "{ 'createdAt': -1 }")
    List<Transaction> findTopNByAccountNumberOrderByCreatedAtDesc(String accountNumber, int numberOfTransactions);
}
