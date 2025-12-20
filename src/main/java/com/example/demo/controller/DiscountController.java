package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.impl.DiscountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@Tag(name = "Discount Management", description = "APIs for evaluating and managing bundle discounts")
public class DiscountController {
    
    private final DiscountServiceImpl discountService;
    
    public DiscountController(DiscountServiceImpl discountService) {
        this.discountService = discountService;
    }
    
    @PostMapping("/evaluate/{cartId}")
    @Operation(summary = "Evaluate and apply bundle discounts for a cart")
    public ResponseEntity<List<DiscountApplication>> evaluateDiscounts(@PathVariable Long cartId) {
        List<DiscountApplication> applications = discountService.evaluateDiscounts(cartId);
        return ResponseEntity.ok(applications);
    }
    
    @GetMapping("/cart/{cartId}")
    @Operation(summary = "Get all discount applications for a cart")
    public ResponseEntity<List<DiscountApplication>> getApplicationsForCart(@PathVariable Long cartId) {
        List<DiscountApplication> applications = discountService.getApplicationsForCart(cartId);
        return ResponseEntity.ok(applications);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get discount application by ID")
    public ResponseEntity<DiscountApplication> getApplication(@PathVariable Long id) {
        DiscountApplication application = discountService.getApplicationById(id);
        return ResponseEntity.ok(application);
    }
}