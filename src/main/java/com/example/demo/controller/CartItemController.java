package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public CartItem addItem(@RequestBody CartItem cartItem) {
        return cartItemService.addItem(cartItem);
    }

    @GetMapping("/{cartId}")
    public List<CartItem> getItems(@PathVariable Long cartId) {
        return cartItemService.getItemsByCartId(cartId);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        cartItemService.removeItem(id);
    }
}
