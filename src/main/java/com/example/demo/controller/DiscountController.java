package com.example.demo.controller;

import com.example.demo.model.BundleRule;
import com.example.demo.service.DiscountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public List<BundleRule> getDiscounts() {
        return discountService.getActiveDiscounts();
    }
}
