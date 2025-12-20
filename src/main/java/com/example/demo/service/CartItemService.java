package com.example.demo.service;

import com.example.demo.model.CartItem;

import java.util.List;

public interface CartItemService {
    
    /**
     * Add an item to cart
     * @param item the cart item to add
     * @return the created or updated cart item
     * @throws IllegalArgumentException if quantity is invalid or cart is inactive
     * @throws jakarta.persistence.EntityNotFoundException if cart or product not found
     */
    CartItem addItemToCart(CartItem item);
    
    /**
     * Update a cart item's quantity
     * @param id the cart item ID
     * @param quantity the new quantity
     * @return the updated cart item
     * @throws IllegalArgumentException if quantity is invalid
     * @throws jakarta.persistence.EntityNotFoundException if cart item not found
     */
    CartItem updateItem(Long id, Integer quantity);
    
    /**
     * Get all items for a cart
     * @param cartId the cart ID
     * @return list of cart items
     */
    List<CartItem> getItemsForCart(Long cartId);
    
    /**
     * Remove an item from cart
     * @param id the cart item ID to remove
     * @throws jakarta.persistence.EntityNotFoundException if cart item not found
     */
    void removeItem(Long id);
}