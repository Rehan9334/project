package com.example.onlinebanking.service;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.model.User;
import com.example.onlinebanking.repository.AccountRepository;
import com.example.onlinebanking.repository.UserRepository;
import com.example.onlinebanking.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount_createsForExistingUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("alice");

        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user));

        Account saved = Account.builder().id(10L).accountNumber("ACCT-123").balance(BigDecimal.valueOf(100)).owner(user).build();
        when(accountRepository.save(any(Account.class))).thenReturn(saved);

        Account result = accountService.createAccount("alice", null, BigDecimal.valueOf(100));

        assertNotNull(result);
        assertEquals("ACCT-123", result.getAccountNumber());
        assertEquals(BigDecimal.valueOf(100).setScale(2), result.getBalance().setScale(2));
        verify(accountRepository, times(1)).save(any(Account.class));
    }
}
