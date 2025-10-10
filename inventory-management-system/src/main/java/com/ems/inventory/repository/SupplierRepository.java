package com.ems.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.inventory.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // You can add custom queries if needed, for example:
    // List<Supplier> findBySupplierNameContaining(String keyword);
}
