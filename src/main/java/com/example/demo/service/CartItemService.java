package com.example.demo.service;

import com.example.demo.model.CartItem;
import java.util.List;

public interface CartItemService {

    CartItem addCartItem(CartItem cartItem);

    CartItem updateCartItem(Long id, CartItem cartItem);

    CartItem getCartItemById(Long id);

    List<CartItem> getAllCartItems();

    void removeCartItem(Long id);
}