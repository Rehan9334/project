package com.example.onlinebanking.service;

import com.example.onlinebanking.model.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account createAccount(String username, String accountNumber, BigDecimal initialBalance);
    Account findById(Long id);
}
