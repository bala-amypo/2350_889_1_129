package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService service;

    public CartItemController(CartItemService service) {
        this.service = service;
    }

    // ➕ Add item to cart
    @PostMapping
    public CartItem addItem(@RequestBody CartItem cartItem) {
        return service.addItem(
                cartItem.getCart().getId(),
                cartItem.getProduct().getId(),
                cartItem.getQuantity()
        );
    }

    // 📦 Get all items for a cart
    @GetMapping("/cart/{cartId}")
    public List<CartItem> getItemsForCart(@PathVariable Long cartId) {
        return service.getItemsForCart(cartId);
    }

    // ✏️ Update quantity
    @PutMapping("/{id}")
    public CartItem updateItem(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return service.updateItem(id, quantity);
    }

    // ❌ Remove item
    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable Long id) {
        service.removeItem(id);
    }
}
