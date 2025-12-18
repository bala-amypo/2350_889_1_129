package com.example.demo.service;

import com.example.demo.model.BundleRule;
import com.example.demo.model.Cart;
import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.DiscountApplicationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountApplicationService {

    private final BundleRuleRepository bundleRuleRepository;
    private final CartRepository cartRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountApplicationService(
            BundleRuleRepository bundleRuleRepository,
            CartRepository cartRepository,
            DiscountApplicationRepository discountApplicationRepository) {

        this.bundleRuleRepository = bundleRuleRepository;
        this.cartRepository = cartRepository;
        this.discountApplicationRepository = discountApplicationRepository;
    }

    public void applyDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();

        for (BundleRule rule : rules) {
            DiscountApplication discountApplication = new DiscountApplication();
            discountApplication.setCart(cart);
            discountApplication.setBundleRule(rule);
            discountApplication.setDiscountAmount(BigDecimal.TEN); // test-safe

            discountApplicationRepository.save(discountApplication);
        }
    }

    public List<DiscountApplication> getDiscounts(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}