package com.example.onlinebanking.service.impl;

import com.example.onlinebanking.dto.TransferRequest;
import com.example.onlinebanking.exception.InsufficientFundsException;
import com.example.onlinebanking.exception.ResourceNotFoundException;
import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.model.Transaction;
import com.example.onlinebanking.repository.AccountRepository;
import com.example.onlinebanking.repository.TransactionRepository;
import com.example.onlinebanking.repository.UserRepository;
import com.example.onlinebanking.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(AccountRepository accountRepository,
                                  TransactionRepository transactionRepository,
                                  UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void transfer(TransferRequest request, String username) {
        Account from = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("From account not found"));

        Account to = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("To account not found"));

        if (!from.getOwner().getUsername().equals(username)) {
            throw new SecurityException("Not authorized to transfer from this account");
        }

        BigDecimal amount = request.getAmount();
        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction debitTx = Transaction.builder()
                .fromAccount(from)
                .toAccount(to)
                .amount(amount)
                .type("DEBIT")
                .description(request.getDescription())
                .build();

        Transaction creditTx = Transaction.builder()
                .fromAccount(from)
                .toAccount(to)
                .amount(amount)
                .type("CREDIT")
                .description(request.getDescription())
                .build();

        transactionRepository.save(debitTx);
        transactionRepository.save(creditTx);
    }
}
