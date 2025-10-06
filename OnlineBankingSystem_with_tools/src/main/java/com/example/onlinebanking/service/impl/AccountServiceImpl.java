package com.example.onlinebanking.service.impl;

import com.example.onlinebanking.exception.ResourceNotFoundException;
import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.model.User;
import com.example.onlinebanking.repository.AccountRepository;
import com.example.onlinebanking.repository.UserRepository;
import com.example.onlinebanking.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Account createAccount(String username, String accountNumber, BigDecimal initialBalance) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        if (accountNumber == null || accountNumber.isBlank()) {
            accountNumber = generateAccountNumber();
        }

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .balance(initialBalance == null ? BigDecimal.ZERO : initialBalance)
                .owner(user)
                .build();

        return accountRepository.save(account);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));
    }

    private String generateAccountNumber() {
        return "ACCT-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();
    }
}
