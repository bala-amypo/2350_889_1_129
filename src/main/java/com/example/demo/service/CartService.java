package com.example.demo.service;

import com.example.demo.model.Cart;
import java.util.List;

public interface CartService {
    Cart addCart(Cart cart);
    List<Cart> getAllCarts();
    Cart getCartById(Long id);
    void deleteCart(Long id);
}
