package com.ems.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.inventory.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}