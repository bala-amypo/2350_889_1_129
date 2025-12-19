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

    public DiscountServiceImpl(DiscountApplicationRepository discountRepo,
                               BundleRuleRepository ruleRepo,
                               CartRepository cartRepo,
                               CartItemRepository itemRepo) {
        this.discountRepo = discountRepo;
        this.ruleRepo = ruleRepo;
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
    }

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow();

        if (!cart.getActive())
            return List.of();

        List<CartItem> items = itemRepo.findByCartId(cartId);
        discountRepo.deleteByCartId(cartId);

        List<DiscountApplication> result = new ArrayList<>();

        for (BundleRule rule : ruleRepo.findByActiveTrue()) {
            Set<Long> required = new HashSet<>();
            for (String s : rule.getRequiredProductIds().split(",")) {
                required.add(Long.valueOf(s.trim()));
            }

            BigDecimal total = BigDecimal.ZERO;
            for (CartItem ci : items) {
                if (required.contains(ci.getProduct().getId())) {
                    total = total.add(
                            ci.getProduct().getPrice()
                                    .multiply(BigDecimal.valueOf(ci.getQuantity()))
                    );
                }
            }

            if (total.compareTo(BigDecimal.ZERO) > 0) {
                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(
                        total.multiply(BigDecimal.valueOf(rule.getDiscountPercentage() / 100))
                );
                app.setAppliedAt(LocalDateTime.now());
                result.add(discountRepo.save(app));
            }
        }
        return result;
    }
}
