package com.example.demo.service;

import com.example.demo.model.BundleRule;

import java.util.List;

public interface BundleRuleService {

    BundleRule save(BundleRule rule);

    List<BundleRule> getActiveRules();
}
