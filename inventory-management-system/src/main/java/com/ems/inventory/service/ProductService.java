package com.ems.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.inventory.model.Product;
import com.ems.inventory.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepo.findById(id).orElseThrow();
        product.setProductName(productDetails.getProductName());
        product.setQuantity(productDetails.getQuantity());
        product.setPrice(productDetails.getPrice());
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
