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

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemServiceImpl(
            CartItemRepository cartItemRepository,
            CartRepository cartRepository,
            ProductRepository productRepository) {

        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    // ✅ ADD ITEM TO CART
    @Override
    public CartItem addItem(Long cartId, Long productId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        if (!cart.getActive()) {
            throw new IllegalArgumentException("Only active carts allowed");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (!product.getActive()) {
            throw new IllegalArgumentException("Product inactive");
        }

        return cartItemRepository
                .findByCartIdAndProductId(cartId, productId)
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + quantity);
                    return cartItemRepository.save(existing);
                })
                .orElseGet(() -> {
                    CartItem item = new CartItem();
                    item.setCart(cart);
                    item.setProduct(product);
                    item.setQuantity(quantity);
                    return cartItemRepository.save(item);
                });
    }

    // ✅ GET ALL ITEMS FOR A CART
    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    // ✅ UPDATE ITEM QUANTITY
    @Override
    public CartItem updateItem(Long id, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));

        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    // ✅ REMOVE ITEM
    @Override
    public void removeItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
