package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service   // 👈 IF THIS IS MISSING → BOOM error
public class CartItemServiceImpl implements CartItemService {

    @Override
    public void addItem() {
        System.out.println("Item added");
    }
}
