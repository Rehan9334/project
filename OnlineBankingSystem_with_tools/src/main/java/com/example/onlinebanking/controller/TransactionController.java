package com.example.onlinebanking.controller;

import com.example.onlinebanking.dto.TransferRequest;
import com.example.onlinebanking.model.Transaction;
import com.example.onlinebanking.repository.TransactionRepository;
import com.example.onlinebanking.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionService transactionService, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@Valid @RequestBody TransferRequest request, Authentication authentication) {
        transactionService.transfer(request, authentication.getName());
        return ResponseEntity.ok("Transfer successful");
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getForAccount(@PathVariable Long accountId, Authentication authentication) {
        List<Transaction> list = transactionRepository.findByFromAccountIdOrToAccountIdOrderByCreatedAtDesc(accountId, accountId);
        return ResponseEntity.ok(list);
    }
}
