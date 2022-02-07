package com.example.billingserviceworker.service;

import com.example.billingshared.dto.Status;
import com.example.billingshared.model.Transaction;
import com.example.billingshared.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceWorker {
    @Autowired
    private TransactionRepository transactionRepository;

    @KafkaListener(topics = "transaction-topic", groupId = "transaction")
    public void charge(Transaction transaction) throws InterruptedException {
        Thread.sleep(100);
        Transaction transaction1 = transactionRepository.findByTransactionRef(transaction.getTransactionRef());
        transaction1.setStatus(Status.SUCCESS.getStatus());
        transactionRepository.save(transaction1);

    }
}
