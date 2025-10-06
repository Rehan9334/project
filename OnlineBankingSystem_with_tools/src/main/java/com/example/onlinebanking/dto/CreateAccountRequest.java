package com.example.onlinebanking.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private String accountNumber;

    @DecimalMin(value = "0.0")
    private BigDecimal initialBalance = BigDecimal.ZERO;
}
