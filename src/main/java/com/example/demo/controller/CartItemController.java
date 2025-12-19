package com.example.demo.controller;

import com.example.demo.service.CartItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public String addItem() {
        cartItemService.addItem();
        return "Item added";
    }
}
