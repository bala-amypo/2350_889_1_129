package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart(Long userId) {
        return cartRepository.findByUserIdAndActiveTrue(userId)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUserId(userId);
                    c.setActive(true);
                    return cartRepository.save(c);
                });
    }

    public Cart getActiveCartForUser(Long userId) {
        return cartRepository.findByUserIdAndActiveTrue(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Active cart not found"));
    }
}
