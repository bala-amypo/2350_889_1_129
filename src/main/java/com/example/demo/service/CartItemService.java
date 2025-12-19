package com.example.demo.service;

import com.example.demo.model.CartItem;
import java.util.List;

public interface CartItemService {
    CartItem addItem(CartItem item);   // Make sure it accepts CartItem
    List<CartItem> getAllItems();
    void deleteItem(Long id);
}
