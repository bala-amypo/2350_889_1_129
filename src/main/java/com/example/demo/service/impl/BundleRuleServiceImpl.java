package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository bundleRuleRepository;

    public BundleRuleServiceImpl(BundleRuleRepository bundleRuleRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
    }

    @Override
    public List<BundleRule> getAllActiveRules() {
        return bundleRuleRepository.findByActiveTrue();
    }

    @Override
    public BundleRule createRule(BundleRule rule) {
        return bundleRuleRepository.save(rule);
    }

    @Override
    public List<BundleRule> getAllRules() {
        return bundleRuleRepository.findAll();
    }
}
