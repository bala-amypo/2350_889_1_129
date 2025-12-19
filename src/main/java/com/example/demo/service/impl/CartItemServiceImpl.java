package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
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

    @Override
    public CartItem addItem(Long cartId, Long productId, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("not found"));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem updateItem(Long id, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public void removeItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
