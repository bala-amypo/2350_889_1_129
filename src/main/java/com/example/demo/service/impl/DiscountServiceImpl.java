package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class DiscountServiceImpl {

    private final DiscountApplicationRepository discountRepo;
    private final BundleRuleRepository ruleRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;

    public DiscountServiceImpl(
            DiscountApplicationRepository discountRepo,
            BundleRuleRepository ruleRepo,
            CartRepository cartRepo,
            CartItemRepository itemRepo) {
        this.discountRepo = discountRepo;
        this.ruleRepo = ruleRepo;
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
    }

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepo.findById(cartId).orElse(null);
        if (cart == null || !cart.getActive()) {
            return List.of();
        }

        discountRepo.deleteByCartId(cartId);

        List<CartItem> items = itemRepo.findByCartId(cartId);
        List<BundleRule> rules = ruleRepo.findByActiveTrue();

        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : rules) {
            String[] ids = rule.getRequiredProductIds().split(",");
            Set<Long> required = new HashSet<>();
            for (String id : ids) required.add(Long.valueOf(id.trim()));

            BigDecimal total = BigDecimal.ZERO;
            Set<Long> found = new HashSet<>();

            for (CartItem ci : items) {
                Long pid = ci.getProduct().getId();
                if (required.contains(pid)) {
                    found.add(pid);
                    total = total.add(
                            ci.getProduct().getPrice()
                                    .multiply(BigDecimal.valueOf(ci.getQuantity()))
                    );
                }
            }

            if (found.containsAll(required) && total.compareTo(BigDecimal.ZERO) > 0) {
                DiscountApplication da = new DiscountApplication();
                da.setCart(cart);
                da.setBundleRule(rule);
                da.setAppliedAt(LocalDateTime.now());
                da.setDiscountAmount(
                        total.multiply(
                                BigDecimal.valueOf(rule.getDiscountPercentage() / 100)
                        )
                );
                result.add(discountRepo.save(da));
            }
        }
        return result;
    }
}

