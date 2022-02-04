package com.example.billing.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundAccountDTO {
    private Long customerId;
    private BigDecimal amount;
}
