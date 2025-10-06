package com.example.onlinebanking.service;

import com.example.onlinebanking.dto.TransferRequest;

public interface TransactionService {
    void transfer(TransferRequest request, String username);
}
