package com.example.customerservice.service.impl;

import com.example.customerservice.dto.FundAccountDTO;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.service.CustomerService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaTemplate<String, FundAccountDTO> fundingTemplate;

    private final String FUND_TOPIC = "funding-topic";

    @Override
    public void fundAccount(FundAccountDTO fundAccountDTO) {
        Customer customer = customerRepository.findById(fundAccountDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found!"));

        fundingTemplate.send(FUND_TOPIC, fundAccountDTO.getCustomerId().toString(), fundAccountDTO);
    }

    @Override
    public List<Customer> fetchCustomer() {
        return customerRepository.findAll();
    }
}
