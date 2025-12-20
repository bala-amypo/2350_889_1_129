package com.example.demo.controller;

import com.example.demo.model.BundleRule;
import com.example.demo.service.impl.BundleRuleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bundle-rules")
@Tag(name = "Bundle Rule Management", description = "APIs for managing bundle discount rules")
public class BundleRuleController {
    
    private final BundleRuleServiceImpl bundleRuleService;
    
    public BundleRuleController(BundleRuleServiceImpl bundleRuleService) {
        this.bundleRuleService = bundleRuleService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new bundle rule")
    public ResponseEntity<BundleRule> createRule(@RequestBody BundleRule rule) {
        BundleRule created = bundleRuleService.createRule(rule);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing bundle rule")
    public ResponseEntity<BundleRule> updateRule(@PathVariable Long id, @RequestBody BundleRule rule) {
        BundleRule updated = bundleRuleService.updateRule(id, rule);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get bundle rule by ID")
    public ResponseEntity<BundleRule> getRule(@PathVariable Long id) {
        BundleRule rule = bundleRuleService.getRuleById(id);
        return ResponseEntity.ok(rule);
    }
    
    @GetMapping("/active")
    @Operation(summary = "Get all active bundle rules")
    public ResponseEntity<List<BundleRule>> getActiveRules() {
        List<BundleRule> rules = bundleRuleService.getActiveRules();
        return ResponseEntity.ok(rules);
    }
    
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a bundle rule")
    public ResponseEntity<Void> deactivateRule(@PathVariable Long id) {
        bundleRuleService.deactivateRule(id);
        return ResponseEntity.noContent().build();
    }
}