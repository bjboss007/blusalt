package com.example.billing.service;
import com.example.billingshared.dto.FundAccountDTO;
import com.example.billingshared.dto.Status;
import com.example.billingshared.model.Transaction;
import com.example.billingshared.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private KafkaTemplate<String, Transaction> billingTemplate;

    private final String TRANSACTION_TOPIC = "transaction-topic";

    @KafkaListener(topics = "funding-topic", groupId = "billing")
    private void saveFunding(FundAccountDTO fundDTO){
        Transaction transaction = new Transaction();
        transaction.setAmount(fundDTO.getAmount());
        transaction.setCustomerId(fundDTO.getCustomerId());
        transaction.setStatus(Status.PENDING.getStatus());
        transactionRepo.save(transaction);
        billingTemplate.send(TRANSACTION_TOPIC, transaction.getTransactionRef(), transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }
}
