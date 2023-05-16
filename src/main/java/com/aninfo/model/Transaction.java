package com.aninfo.model;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long transactionId;

    public Long cbu;
    public Double amount;

    public String type;

    public Transaction(Long cbu, Double amount, String type) {
        this.amount = amount;
        this.type = type;
        this.cbu = cbu;
    }

    public double getCbu() { return cbu;}

    public void setTransactionId(Long id){
        this.transactionId = id;
    }

    public void withdrawTransaction(Account account) {
        if (account.getBalance() < this.amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - this.amount);
    }

    public void depositTransaction(Account account) {
        Double res = this.amount;
        if (res <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }


        if (res >= 2000 && (res * 0.1 < 500)) res = res * 1.1;
        else if (res >= 2000 && (res * 0.1 >= 500)) res += 500;

        account.setBalance(account.getBalance() + res);
    }

}