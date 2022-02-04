package com.example.customerservice.api;


import com.example.customerservice.dto.FundAccountDTO;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("all")
    public List<Customer> getALlCustomer(){
        return customerService.fetchCustomer();
    }

    @PostMapping("fund")
    public Map<String, String> fundAccount(@RequestBody FundAccountDTO fundAccountDTO){
        customerService.fundAccount(fundAccountDTO);
        Map<String, String> response = new HashMap<>();
        response.put("response","success");
        response.put("message","Customer account successfully funded");
        return response;
    }
}
