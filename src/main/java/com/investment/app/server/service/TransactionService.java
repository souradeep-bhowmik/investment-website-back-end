package com.investment.app.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.investment.app.server.converter.transaction.TransactionConverter;
import com.investment.app.server.entity.Transaction;
import com.investment.app.server.repository.TransactionRepository;
import com.investment.app.server.model.CostBasisResponse;
import com.investment.app.server.model.TransactionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

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

    public List<CostBasisResponse> returnCostBasis() {
        List<Transaction> transactions = getAllTransactions();
        List<Transaction> buyRecordList = new ArrayList<Transaction>();
        List<CostBasisResponse> resultList = new ArrayList<CostBasisResponse>();
        Transaction sellTransaction;
        int start = 0;
        int end = 0;

        for (int i = 0; i < transactions.size(); i++) {
            Double profit;
            Double originalCost;
            Double currentCost;
            Date buyDate = new Date();
            if (transactions.get(i).getSize() > 0) { // Buy record, so add it to the temp array and increase end pointer
                buyRecordList.add(end, transactions.get(i));
                end++;
            } else { // Sell record, so add business logic to process in this block
                profit = originalCost = 0.0;
                Gson gson = new Gson();
                String jsonString = gson.toJson(transactions.get(i));
                sellTransaction = gson.fromJson(jsonString, Transaction.class);
                // byte[] bytes = SerializationUtils.serialize(transactions.get(i));
                // sellTransaction = (Transaction) SerializationUtils.deserialize(bytes);
                sellTransaction.setSize(sellTransaction.getSize() * (-1));
                while (sellTransaction.getSize() > 0) { // While the sell record still has stocks to sell
                    currentCost = 0.0;
                    if (buyRecordList.get(start).getSize() >= sellTransaction.getSize()) { // If the first buy record
                                                                                           // can
                                                                                           // satisfy the sell
                        currentCost += ((sellTransaction.getSize() * buyRecordList.get(start).getPrice())
                                + (sellTransaction.getSize() / buyRecordList.get(start).getSize()
                                        * buyRecordList.get(start).getFees()));
                        originalCost += currentCost;
                        profit += ((sellTransaction.getSize() * sellTransaction.getPrice() - sellTransaction.getFees())
                                - currentCost);
                        buyDate = buyRecordList.get(start).getDate();
                        buyRecordList.get(start)
                                .setSize(buyRecordList.get(start).getSize() - sellTransaction.getSize());
                        sellTransaction.setSize(0.0);
                        if (buyRecordList.get(start).getSize() == 0.0) {
                            start += 1;
                        }
                    } else { // If more than one buy record is needed to satisfy the sell
                        currentCost += ((buyRecordList.get(start).getSize() * buyRecordList.get(start).getPrice())
                                + buyRecordList.get(start).getFees());
                        originalCost += currentCost;
                        profit += ((buyRecordList.get(start).getSize() * sellTransaction.getPrice())
                                - (buyRecordList.get(start).getSize() / sellTransaction.getSize()
                                        * sellTransaction.getFees()))
                                - currentCost;
                        sellTransaction.setSize(sellTransaction.getSize() - buyRecordList.get(start).getSize());
                        buyRecordList.get(start).setSize(0.0);
                        start += 1;
                    }
                }
                resultList
                        .add(new CostBasisResponse(transactions.get(i).getDate(), buyDate,
                                transactions.get(i).getSize() * (-1), transactions.get(i).getPrice(),
                                transactions.get(i).getFees(),
                                (transactions.get(i).getSize() * (-1) * transactions.get(i).getPrice())
                                        - transactions.get(i).getFees(),
                                originalCost, profit, profit / originalCost * 100));
            }
        }
        return resultList;
    }
}
