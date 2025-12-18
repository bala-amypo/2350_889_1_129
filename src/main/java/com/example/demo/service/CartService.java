package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {
    Cart createCart(Long userId);
    Cart getCart(Long cartId);
    Cart getCartByUserId(Long userId);
    void deleteCart(Long cartId);
}
