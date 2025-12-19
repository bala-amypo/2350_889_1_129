package com.example.demo.service;

import com.example.demo.model.CartItem;
import java.util.List;

public interface CartService {

    CartItem addToCart(CartItem cartItem);

    List<CartItem> getAllCartItems();

    void removeFromCart(Long id);
}
