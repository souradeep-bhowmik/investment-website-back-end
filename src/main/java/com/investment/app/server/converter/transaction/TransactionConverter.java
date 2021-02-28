package com.investment.app.server.converter.transaction;

import java.util.function.Function;

import com.investment.app.server.entity.Transaction;
import com.investment.app.server.model.TransactionRequest;

public class TransactionConverter {

    public static final Function<TransactionRequest, Transaction> TO_MODEL = request -> {
        Transaction transaction = new Transaction();
        transaction.setUser(request.getUser());
        transaction.setProduct(request.getProduct());
        transaction.setDate(request.getDate());
        transaction.setSize(request.getSize());
        transaction.setPrice(request.getPrice());
        transaction.setFees(request.getFees());
        return transaction;
    };

    private TransactionConverter() {
        // static only
    }
}
