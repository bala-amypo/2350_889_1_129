package com.example.demo.service;

import com.example.demo.model.BundleRule;
import java.util.List;

public interface DiscountService {
    List<BundleRule> getActiveDiscounts();
}
