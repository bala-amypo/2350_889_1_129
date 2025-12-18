package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountApplicationController {

    private final DiscountApplicationService discountApplicationService;

    public DiscountApplicationController(
            DiscountApplicationService discountApplicationService) {

        this.discountApplicationService = discountApplicationService;
    }

    @PostMapping("/apply/{cartId}")
    public void applyDiscounts(@PathVariable Long cartId) {
        discountApplicationService.applyDiscounts(cartId);
    }

    @GetMapping("/{cartId}")
    public List<DiscountApplication> getDiscounts(@PathVariable Long cartId) {
        return discountApplicationService.getDiscounts(cartId);
    }
}
