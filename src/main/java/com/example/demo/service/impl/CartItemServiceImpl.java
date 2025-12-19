package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CartItemServiceImpl {

    private final CartItemRepository itemRepo;
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartItemServiceImpl(CartItemRepository itemRepo,
                               CartRepository cartRepo,
                               ProductRepository productRepo) {
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public CartItem addItemToCart(CartItem item) {
        if (item.getQuantity() <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        Cart cart = cartRepo.findById(item.getCart().getId())
                .orElseThrow(EntityNotFoundException::new);

        if (!cart.getActive())
            throw new IllegalArgumentException("active carts only");

        Product product = productRepo.findById(item.getProduct().getId())
                .orElseThrow(EntityNotFoundException::new);

        return itemRepo.findByCartIdAndProductId(cart.getId(), product.getId())
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + item.getQuantity());
                    return itemRepo.save(existing);
                })
                .orElseGet(() -> {
                    item.setCart(cart);
                    item.setProduct(product);
                    return itemRepo.save(item);
                });
    }

    public List<CartItem> getItemsForCart(Long cartId) {
        return itemRepo.findByCartId(cartId);
    }
}
