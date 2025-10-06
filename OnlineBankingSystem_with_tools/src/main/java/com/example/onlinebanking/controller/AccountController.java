package com.example.onlinebanking.controller;

import com.example.onlinebanking.dto.CreateAccountRequest;
import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest request,
                                           Authentication authentication) {
        String username = authentication.getName();
        Account created = accountService.createAccount(username, request.getAccountNumber(), request.getInitialBalance());
        return ResponseEntity.created(URI.create("/api/accounts/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id, Authentication authentication) {
        Account account = accountService.findById(id);
        if (!account.getOwner().getUsername().equals(authentication.getName())) {
            return ResponseEntity.status(403).body("Forbidden");
        }
        return ResponseEntity.ok(account);
    }
}
