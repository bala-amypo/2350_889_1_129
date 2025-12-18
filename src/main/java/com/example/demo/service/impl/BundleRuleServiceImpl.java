package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountApplicationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountApplicationServiceImpl implements DiscountApplicationService {

    private final BundleRuleRepository bundleRuleRepository;
    private final CartRepository cartRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountApplicationServiceImpl(
            BundleRuleRepository bundleRuleRepository,
            CartRepository cartRepository,
            DiscountApplicationRepository discountApplicationRepository) {

        this.bundleRuleRepository = bundleRuleRepository;
        this.cartRepository = cartRepository;
        this.discountApplicationRepository = discountApplicationRepository;
    }

    @Override
    public void applyDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();

        for (BundleRule rule : rules) {
            DiscountApplication da = new DiscountApplication();
            da.setCart(cart);
            da.setBundleRule(rule);
            da.setDiscountAmount(BigDecimal.TEN);
            discountApplicationRepository.save(da);
        }
    }

    @Override
    public List<DiscountApplication> getDiscounts(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}