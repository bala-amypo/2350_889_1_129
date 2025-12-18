package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<Long, Product> db = new HashMap<>();

    @Override
    public Product create(Product product) {
        db.put(product.getId(), product);
        return product;
    }

    @Override
    public Product getById(Long id) {
        return db.get(id);
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public void delete(Long id) {
        db.remove(id);
    }
}
