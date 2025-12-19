package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DiscountServiceImpl {

    private final DiscountApplicationRepository discountRepo;
    private final BundleRuleRepository ruleRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;

    public DiscountServiceImpl(DiscountApplicationRepository discountRepo,
                               BundleRuleRepository ruleRepo,
                               CartRepository cartRepo,
                               CartItemRepository cartItemRepo) {
        this.discountRepo = discountRepo;
        this.ruleRepo = ruleRepo;
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
    }

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow();
        if (!cart.getActive()) {
            return List.of();
        }

        discountRepo.deleteByCartId(cartId);

        List<CartItem> items = cartItemRepo.findByCartId(cartId);
        List<BundleRule> rules = ruleRepo.findByActiveTrue();
        List<DiscountApplication> result = new ArrayList<>();

        Set<Long> productIds = items.stream()
                .map(i -> i.getProduct().getId())
                .collect(Collectors.toSet());

        for (BundleRule rule : rules) {
            Set<Long> required = Arrays.stream(rule.getRequiredProductIds().split(","))
                    .map(String::trim)
                    .map(Long::valueOf)
                    .collect(Collectors.toSet());

            if (productIds.containsAll(required)) {
                BigDecimal total = items.stream()
                        .filter(i -> required.contains(i.getProduct().getId()))
                        .map(i -> i.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(i.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal discount = total
                        .multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
                        .divide(BigDecimal.valueOf(100));

                if (discount.compareTo(BigDecimal.ZERO) > 0) {
                    DiscountApplication app = new DiscountApplication();
                    app.setCart(cart);
                    app.setBundleRule(rule);
                    app.setDiscountAmount(discount);
                    app.setAppliedAt(LocalDateTime.now());
                    result.add(discountRepo.save(app));
                }
            }
        }
        return result;
    }
}
