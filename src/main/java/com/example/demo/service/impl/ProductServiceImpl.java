package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;

public class ProductServiceImpl {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    public Product createProduct(Product p) {
        if (repo.findBySku(p.getSku()).isPresent())
            throw new IllegalArgumentException("SKU already exists");

        if (p.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price must be positive");

        p.setActive(true);
        return repo.save(p);
    }

    public Product updateProduct(Long id, Product data) {
        Product p = getProductById(id);
        p.setName(data.getName());
        p.setPrice(data.getPrice());
        return repo.save(p);
    }

    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public void deactivateProduct(Long id) {
        Product p = getProductById(id);
        p.setActive(false);
        repo.save(p);
    }
}
