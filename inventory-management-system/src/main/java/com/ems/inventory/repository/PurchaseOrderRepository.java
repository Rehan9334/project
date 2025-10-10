package com.ems.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.inventory.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    // Example of a custom query if needed:
    // List<PurchaseOrder> findByStatus(String status);
}
