package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CartItemServiceImpl {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                               CartRepository cartRepository,
                               ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartItem addItemToCart(CartItem item) {
        Cart cart = cartRepository.findById(item.getCart().getId())
                .orElseThrow(EntityNotFoundException::new);
        if (!cart.getActive()) {
            throw new IllegalArgumentException("active carts");
        }
        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(EntityNotFoundException::new);
        if (!product.getActive()) {
            throw new IllegalArgumentException("Product inactive");
        }
        if (item.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        return cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + item.getQuantity());
                    return cartItemRepository.save(existing);
                })
                .orElseGet(() -> cartItemRepository.save(item));
    }

    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}
