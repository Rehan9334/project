package com.example.onlinebanking.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinebanking.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
