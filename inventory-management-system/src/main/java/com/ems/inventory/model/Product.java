package com.ems.inventory.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String category;
    private int quantity;
    private double price;
    
    

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
