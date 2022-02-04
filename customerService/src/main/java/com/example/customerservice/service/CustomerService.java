package com.example.customerservice.service;


import com.example.customerservice.dto.FundAccountDTO;
import com.example.customerservice.model.Customer;

import java.util.List;

public interface CustomerService {
    void fundAccount(FundAccountDTO fundAccountDTO);
    List<Customer> fetchCustomer();
}
