package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {
    
    private final BundleRuleRepository bundleRuleRepository;
    
    public BundleRuleServiceImpl(BundleRuleRepository bundleRuleRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
    }
    
    @Override
    @Transactional
    public BundleRule createRule(BundleRule rule) {
        // Validate discount percentage is between 0 and 100
        if (rule.getDiscountPercentage() == null || 
            rule.getDiscountPercentage() < 0 || 
            rule.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Invalid discount: must be between 0 and 100");
        }
        
        // Validate required product IDs is not empty
        if (rule.getRequiredProductIds() == null || 
            rule.getRequiredProductIds().trim().isEmpty()) {
            throw new IllegalArgumentException("Required products cannot be empty");
        }
        
        return bundleRuleRepository.save(rule);
    }
    
    @Override
    @Transactional
    public BundleRule updateRule(Long id, BundleRule rule) {
        BundleRule existing = bundleRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bundle rule not found"));
        
        if (rule.getRuleName() != null) {
            existing.setRuleName(rule.getRuleName());
        }
        if (rule.getRequiredProductIds() != null) {
            if (rule.getRequiredProductIds().trim().isEmpty()) {
                throw new IllegalArgumentException("Required products cannot be empty");
            }
            existing.setRequiredProductIds(rule.getRequiredProductIds());
        }
        if (rule.getDiscountPercentage() != null) {
            if (rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
                throw new IllegalArgumentException("Invalid discount: must be between 0 and 100");
            }
            existing.setDiscountPercentage(rule.getDiscountPercentage());
        }
        
        return bundleRuleRepository.save(existing);
    }
    
    @Override
    public BundleRule getRuleById(Long id) {
        return bundleRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bundle rule not found"));
    }
    
    @Override
    public List<BundleRule> getActiveRules() {
        return bundleRuleRepository.findByActiveTrue();
    }
    
    @Override
    @Transactional
    public void deactivateRule(Long id) {
        BundleRule rule = bundleRuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bundle rule not found"));
        rule.setActive(false);
        bundleRuleRepository.save(rule);
    }
}