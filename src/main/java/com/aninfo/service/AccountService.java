package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double amount) {
        Account account = accountRepository.findAccountByCbu(cbu);
        Transaction transaction = new Transaction(cbu, amount, "Withdraw");
        transaction.withdrawTransaction(account);
        accountRepository.save(account);
        transactionRepository.createTransaction(transaction);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double amount) {

        Account account = accountRepository.findAccountByCbu(cbu);
        Transaction transaction = new Transaction(cbu, amount, "Deposit");
        transaction.depositTransaction(account);
        accountRepository.save(account);
        transactionRepository.createTransaction(transaction);

        return account;
    }

}