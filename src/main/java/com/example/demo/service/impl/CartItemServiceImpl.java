package com.example.demo.service.impl;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final Map<Long, List<CartItem>> db = new HashMap<>();

    @Override
    public CartItem addItem(Long cartId, Long productId, Integer quantity) {
        CartItem item = new CartItem(cartId, productId, quantity);
        db.computeIfAbsent(cartId, k -> new ArrayList<>()).add(item);
        return item;
    }

    @Override
    public List<CartItem> getItemsByCart(Long cartId) {
        return db.getOrDefault(cartId, new ArrayList<>());
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        // demo stub
    }
}
