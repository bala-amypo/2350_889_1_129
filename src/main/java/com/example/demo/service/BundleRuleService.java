package com.example.demo.service;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BundleRuleService {

    private final BundleRuleRepository bundleRuleRepository;

    public BundleRuleService(BundleRuleRepository bundleRuleRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
    }

    public BundleRule createBundleRule(BundleRule rule) {
        if (rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Invalid discount");
        }
        return bundleRuleRepository.save(rule);
    }

    public List<BundleRule> getActiveRules() {
        return bundleRuleRepository.findByActiveTrue();
    }
}