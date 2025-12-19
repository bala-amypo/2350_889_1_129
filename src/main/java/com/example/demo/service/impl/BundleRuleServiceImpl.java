package com.example.demo.service;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository repository;

    public BundleRuleServiceImpl(BundleRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BundleRule save(BundleRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<BundleRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
}
