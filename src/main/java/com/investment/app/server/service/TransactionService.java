package com.investment.app.server.service;

import java.util.List;

import com.investment.app.server.converter.transaction.TransactionConverter;
import com.investment.app.server.entity.Transaction;
import com.investment.app.server.repository.TransactionRepository;
import com.investment.app.server.model.TransactionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public String saveTransactions(List<TransactionRequest> transactions, String user) {
        Transaction transaction;
        for (TransactionRequest request : transactions) {
            transaction = TransactionConverter.TO_MODEL.apply(request);
            transaction.setUser(user);
            transactionRepository.save(transaction);
        }
        return "Added all transactions!";
    }

}
