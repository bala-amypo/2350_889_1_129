package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    
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
    
    @Override
    @Transactional
    public CartItem addItemToCart(CartItem item) {
        // Validate quantity is positive
        if (item.getQuantity() == null || item.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        // Load cart
        Cart cart = cartRepository.findById(item.getCart().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        
        // Check if cart is active
        if (!cart.getActive()) {
            throw new IllegalArgumentException("Cannot add items to inactive carts");
        }
        
        // Load product
        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        
        // Check if product is active
        if (!product.getActive()) {
            throw new IllegalArgumentException("Cannot add inactive products");
        }
        
        // Check if item already exists for this cart and product
        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(
                cart.getId(), product.getId());
        
        if (existingItem.isPresent()) {
            // Aggregate quantity
            CartItem existing = existingItem.get();
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
            return cartItemRepository.save(existing);
        } else {
            // Create new item
            item.setCart(cart);
            item.setProduct(product);
            return cartItemRepository.save(item);
        }
    }
    
    @Override
    @Transactional
    public CartItem updateItem(Long id, Integer quantity) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));
        
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }
    
    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
    
    @Override
    @Transactional
    public void removeItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new EntityNotFoundException("Cart item not found");
        }
        cartItemRepository.deleteById(id);
    }
}