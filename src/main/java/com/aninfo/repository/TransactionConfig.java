package com.aninfo.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean("transactionRepository")
    public TransactionRepository transactionRepository() {
        return new TransactionRepositoryDefinition();
    }
}