package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount-applications")
public class DiscountApplicationController {

    private final DiscountApplicationService discountApplicationService;

    public DiscountApplicationController(
            DiscountApplicationService discountApplicationService) {
        this.discountApplicationService = discountApplicationService;
    }

    @GetMapping("/{id}")
    public DiscountApplication getById(@PathVariable Long id) {
        return discountApplicationService.getApplicationById(id);
    }

    @GetMapping("/cart/{cartId}")
    public List<DiscountApplication> getByCart(@PathVariable Long cartId) {
        return discountApplicationService.getApplicationsForCart(cartId);
    }
}
