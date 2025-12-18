package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountApplicationController {

    private final DiscountApplicationService service;

    public DiscountApplicationController(DiscountApplicationService service) {
        this.service = service;
    }

    @GetMapping("/{cartId}")
    public List<DiscountApplication> getByCart(@PathVariable Long cartId) {
        return service.getDiscountsByCart(cartId);
    }
}
