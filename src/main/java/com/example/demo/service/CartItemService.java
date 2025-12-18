package com.example.demo.service;

import com.example.demo.model.CartItem;
import java.util.List;

public interface CartItemService {
    CartItem addItem(Long cartId, Long productId, Integer quantity);
    List<CartItem> getItemsByCart(Long cartId);
    void removeCartItem(Long cartItemId);
}
