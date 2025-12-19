package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service   
public class CartItemServiceImpl implements CartItemService {

    @Override
    public void addItem() {
        System.out.println("Item added");
    }
}
