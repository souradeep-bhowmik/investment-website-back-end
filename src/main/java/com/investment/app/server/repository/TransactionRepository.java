package com.investment.app.server.repository;

import com.investment.app.server.entity.Transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    public Transaction getByUser(String user);
}
