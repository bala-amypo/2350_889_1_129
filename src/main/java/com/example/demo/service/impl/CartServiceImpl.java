package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private final Map<Long, Cart> db = new HashMap<>();

    @Override
    public Cart createCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        db.put(userId, cart);
        return cart;
    }

    @Override
    public Cart getCart(Long cartId) {
        return db.get(cartId);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return db.get(userId);
    }

    @Override
    public void deleteCart(Long cartId) {
        db.remove(cartId);
    }
}
