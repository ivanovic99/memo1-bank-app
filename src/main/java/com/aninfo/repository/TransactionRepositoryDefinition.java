package com.aninfo.repository;


import com.aninfo.model.Transaction;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.*;
@RepositoryRestResource
public class TransactionRepositoryDefinition implements TransactionRepository {
    private Map<Long, Transaction> transactionMap;
    private Long transactionId;

    public TransactionRepositoryDefinition() {
        this.transactionMap = new HashMap<>();
        this.transactionId = 0L;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setTransactionId(transactionId);
        transactionMap.put(transactionId, transaction);
        this.transactionId++;
        return transaction;
    }

    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        for (Transaction transaction : transactionMap.values()) {
            allTransactions.add(transaction);
        }
        return allTransactions;
    }

    @Override
    public Transaction getTransaction(long transactionId){
        return transactionMap.get(transactionId);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(long cbu) {
        List<Transaction> transactionsByAccount = new ArrayList<>();
        for (Transaction transaction : transactionMap.values()) {
            if (transaction.getCbu() == cbu) {
                transactionsByAccount.add(transaction);
            }
        }
        return transactionsByAccount;
    }

    @Override
    public void eliminateTransaction(long transactionId) {
        transactionMap.remove(transactionId);
    }
}