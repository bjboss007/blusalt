package com.example.billingserviceworker.service;

import com.example.billing.dto.Status;
import com.example.billing.model.Transaction;
import com.example.billing.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceWorker {
    @Autowired
    private TransactionRepository transactionRepository;

    @KafkaListener(topics = "transaction-topic", groupId = "transaction")
    public void charge(Transaction transaction) throws InterruptedException {
        System.out.println("COunt number " +transactionRepository.count());
        System.out.println("I go this : "+ transaction);
        Thread.sleep(100);
        Transaction transaction1 = transactionRepository.findByTransactionRef(transaction.getTransactionRef());
        transaction1.setStatus(Status.SUCCESS.getStatus());
        transactionRepository.save(transaction1);

    }
}
