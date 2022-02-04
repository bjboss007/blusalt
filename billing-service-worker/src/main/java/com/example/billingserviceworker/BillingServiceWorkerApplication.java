package com.example.billingserviceworker;

import com.example.billing.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.example.billing.repository"})
@ComponentScan(basePackages = {"com.example.billingserviceworker", "com.example.billing.repository" })
@EntityScan({"com.example.billing.model"})
public class BillingServiceWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceWorkerApplication.class, args);
    }

}
