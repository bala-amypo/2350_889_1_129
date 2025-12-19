package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.impl.DiscountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountServiceImpl service;

    public DiscountController(DiscountServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/evaluate/{cartId}")
    public List<DiscountApplication> evaluate(@PathVariable Long cartId) {
        return service.evaluateDiscounts(cartId);
    }
}
