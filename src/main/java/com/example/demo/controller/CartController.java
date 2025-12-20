package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.impl.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Cart Management", description = "APIs for managing shopping carts")
public class CartController {
    
    private final CartServiceImpl cartService;
    
    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }
    
    @PostMapping("/{userId}")
    @Operation(summary = "Create a cart for a user")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId) {
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get cart by ID")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get active cart for a user")
    public ResponseEntity<Cart> getCartByUser(@PathVariable Long userId) {
        Cart cart = cartService.getActiveCartForUser(userId);
        return ResponseEntity.ok(cart);
    }
    
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a cart")
    public ResponseEntity<Void> deactivateCart(@PathVariable Long id) {
        cartService.deactivateCart(id);
        return ResponseEntity.noContent().build();
    }
}