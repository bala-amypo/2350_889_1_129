package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService service;

    public DiscountController(DiscountService service) {
        this.service = service;
    }

    @PostMapping("/apply/{cartId}")
    public DiscountApplication apply(@PathVariable Long cartId) {
        return service.applyDiscount(cartId);
    }

    @GetMapping("/cart/{cartId}")
    public List<DiscountApplication> list(@PathVariable Long cartId) {
        return service.getDiscountsForCart(cartId);
    }
}
