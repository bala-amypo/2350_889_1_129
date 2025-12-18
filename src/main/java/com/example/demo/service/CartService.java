package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {

    Cart createCart(Cart cart);

    Cart getCartById(Long id);

    Cart updateCart(Long id, Cart cart);

    void deleteCart(Long id);
}