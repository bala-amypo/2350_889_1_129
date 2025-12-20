package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.impl.CartItemServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
@Tag(name = "Cart Item Management", description = "APIs for managing items in shopping carts")
public class CartItemController {
    
    private final CartItemServiceImpl cartItemService;
    
    public CartItemController(CartItemServiceImpl cartItemService) {
        this.cartItemService = cartItemService;
    }
    
    @PostMapping
    @Operation(summary = "Add an item to cart")
    public ResponseEntity<CartItem> addItem(@RequestBody CartItem item) {
        CartItem added = cartItemService.addItemToCart(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update cart item quantity")
    public ResponseEntity<CartItem> updateItem(@PathVariable Long id, @RequestParam Integer quantity) {
        CartItem updated = cartItemService.updateItem(id, quantity);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/cart/{cartId}")
    @Operation(summary = "Get all items for a cart")
    public ResponseEntity<List<CartItem>> getItemsForCart(@PathVariable Long cartId) {
        List<CartItem> items = cartItemService.getItemsForCart(cartId);
        return ResponseEntity.ok(items);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove an item from cart")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        cartItemService.removeItem(id);
        return ResponseEntity.noContent().build();
    }
}