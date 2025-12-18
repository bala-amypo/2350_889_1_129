package com.example.demo.controller;

import com.example.demo.model.BundleRule;
import com.example.demo.service.BundleRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bundles")
public class BundleRuleController {

    private final BundleRuleService bundleRuleService;

    public BundleRuleController(BundleRuleService bundleRuleService) {
        this.bundleRuleService = bundleRuleService;
    }

    @PostMapping
    public BundleRule createBundleRule(@RequestBody BundleRule rule) {
        return bundleRuleService.createBundleRule(rule);
    }

    @GetMapping("/active")
    public List<BundleRule> getActiveRules() {
        return bundleRuleService.getActiveRules();
    }
}
