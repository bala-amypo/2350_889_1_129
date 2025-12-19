package com.example.demo.service;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔥 THIS IS THE MOST IMPORTANT LINE
public class DiscountServiceImpl implements DiscountService {

    private final BundleRuleRepository bundleRuleRepository;

    public DiscountServiceImpl(BundleRuleRepository bundleRuleRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
    }

    @Override
    public List<BundleRule> getActiveDiscounts() {
        return bundleRuleRepository.findByActiveTrue();
    }
}
