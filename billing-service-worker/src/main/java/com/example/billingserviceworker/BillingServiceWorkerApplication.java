package com.example.billingserviceworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.billingserviceworker","com.example.billingshared" })
@EnableJpaRepositories(basePackages = {"com.example.billingshared.repository"})
@EntityScan(basePackages = {"com.example.billingshared.model"})
public class BillingServiceWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceWorkerApplication.class, args);
    }

}
