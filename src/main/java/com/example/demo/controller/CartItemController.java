package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addItem(@RequestBody CartItem item) {
        CartItem savedItem = cartItemService.addItem(item);
        return ResponseEntity.ok(savedItem);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartItem>> getAllItems() {
        return ResponseEntity.ok(cartItemService.getAllItems());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        cartItemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}
