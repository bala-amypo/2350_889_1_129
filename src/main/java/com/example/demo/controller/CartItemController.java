package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.impl.CartItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemServiceImpl service;

    public CartItemController(CartItemServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public CartItem add(@RequestBody CartItem item) {
        return service.addItemToCart(item);
    }

    @GetMapping("/cart/{cartId}")
    public List<CartItem> list(@PathVariable Long cartId) {
        return service.getItemsForCart(cartId);
    }
}
