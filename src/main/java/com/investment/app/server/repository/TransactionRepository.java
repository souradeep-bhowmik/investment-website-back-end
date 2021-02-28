package com.investment.app.server.repository;

import com.investment.app.server.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public Transaction getByUser(String user);
}
