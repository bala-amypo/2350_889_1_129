package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {
    
    /**
     * Create a new cart for a user
     * @param userId the user ID
     * @return the created cart
     * @throws IllegalArgumentException if user already has an active cart
     */
    Cart createCart(Long userId);
    
    /**
     * Get the active cart for a user
     * @param userId the user ID
     * @return the active cart
     * @throws jakarta.persistence.EntityNotFoundException if no active cart found
     */
    Cart getActiveCartForUser(Long userId);
    
    /**
     * Get a cart by ID
     * @param id the cart ID
     * @return the cart
     * @throws jakarta.persistence.EntityNotFoundException if cart not found
     */
    Cart getCartById(Long id);
    
    /**
     * Deactivate a cart
     * @param id the cart ID to deactivate
     * @throws jakarta.persistence.EntityNotFoundException if cart not found
     */
    void deactivateCart(Long id);
}