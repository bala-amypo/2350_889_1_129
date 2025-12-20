package com.example.demo.service;

import com.example.demo.model.DiscountApplication;

import java.util.List;

public interface DiscountService {
    
    /**
     * Evaluate and apply bundle discounts for a cart
     * @param cartId the cart ID
     * @return list of discount applications
     */
    List<DiscountApplication> evaluateDiscounts(Long cartId);
    
    /**
     * Get a discount application by ID
     * @param id the discount application ID
     * @return the discount application
     * @throws jakarta.persistence.EntityNotFoundException if discount application not found
     */
    DiscountApplication getApplicationById(Long id);
    
    /**
     * Get all discount applications for a cart
     * @param cartId the cart ID
     * @return list of discount applications
     */
    List<DiscountApplication> getApplicationsForCart(Long cartId);
}