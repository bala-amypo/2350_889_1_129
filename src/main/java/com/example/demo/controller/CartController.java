package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userId}")
    public Cart create(@PathVariable Long userId) {
        return cartService.createCart(userId);
    }

    @GetMapping("/{id}")
    public Cart getById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @GetMapping("/user/{userId}")
    public Cart getByUser(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        cartService.deactivateCart(id);
    }
}
