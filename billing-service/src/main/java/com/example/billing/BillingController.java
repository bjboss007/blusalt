package com.example.billing;


import com.example.billing.model.Transaction;
import com.example.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/billings")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping
    public List<Transaction> fetchAll(){
        return billingService.getAllTransactions();
    }
}
