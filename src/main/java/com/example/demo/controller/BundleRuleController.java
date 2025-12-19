package com.example.demo.controller;

import com.example.demo.model.BundleRule;
import com.example.demo.service.BundleRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bundle-rules")
public class BundleRuleController {

    private final BundleRuleService bundleRuleService;

    public BundleRuleController(BundleRuleService bundleRuleService) {
        this.bundleRuleService = bundleRuleService;
    }

    @PostMapping
    public BundleRule create(@RequestBody BundleRule rule) {
        return bundleRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public BundleRule update(@PathVariable Long id,
                             @RequestBody BundleRule rule) {
        return bundleRuleService.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public BundleRule getById(@PathVariable Long id) {
        return bundleRuleService.getRuleById(id);
    }

    @GetMapping
    public List<BundleRule> getActive() {
        return bundleRuleService.getActiveRules();
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        bundleRuleService.deactivateRule(id);
    }
}
