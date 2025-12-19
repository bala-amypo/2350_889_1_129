package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BundleRuleRepository bundleRuleRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            BundleRuleRepository bundleRuleRepository,
            DiscountApplicationRepository discountApplicationRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.bundleRuleRepository = bundleRuleRepository;
        this.discountApplicationRepository = discountApplicationRepository;
    }

    @Override
    public void evaluateDiscounts(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("not found"));

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        Set<Long> productIds = new HashSet<>();

        for (CartItem item : items) {
            productIds.add(item.getProduct().getId());
        }

        for (BundleRule rule : bundleRuleRepository.findByActiveTrue()) {
            String[] required = rule.getRequiredProductIds().split(",");
            boolean match = true;

            for (String id : required) {
                if (!productIds.contains(Long.parseLong(id.trim()))) {
                    match = false;
                    break;
                }
            }

            if (match) {
                DiscountApplication da = new DiscountApplication();
                da.setCart(cart);
                da.setBundleRule(rule);
                da.setDiscountAmount(BigDecimal.valueOf(rule.getDiscountPercentage()));
                discountApplicationRepository.save(da);
            }
        }
    }

    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}
