package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    
    private final DiscountApplicationRepository discountApplicationRepository;
    private final BundleRuleRepository bundleRuleRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    
    public DiscountServiceImpl(DiscountApplicationRepository discountApplicationRepository,
                               BundleRuleRepository bundleRuleRepository,
                               CartRepository cartRepository,
                               CartItemRepository cartItemRepository) {
        this.discountApplicationRepository = discountApplicationRepository;
        this.bundleRuleRepository = bundleRuleRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }
    
    @Override
    @Transactional
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        // Load cart
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null || !cart.getActive()) {
            return Collections.emptyList();
        }
        
        // Load cart items
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        if (cartItems.isEmpty()) {
            return Collections.emptyList();
        }
        
        // Get product IDs in cart
        Set<Long> cartProductIds = cartItems.stream()
                .map(item -> item.getProduct().getId())
                .collect(Collectors.toSet());
        
        // Load active bundle rules
        List<BundleRule> activeRules = bundleRuleRepository.findByActiveTrue();
        
        // Clear previous discount applications
        discountApplicationRepository.deleteByCartId(cartId);
        
        List<DiscountApplication> applications = new ArrayList<>();
        
        // Evaluate each rule
        for (BundleRule rule : activeRules) {
            // Parse required product IDs
            Set<Long> requiredIds = parseProductIds(rule.getRequiredProductIds());
            
            // Check if cart contains all required products
            if (cartProductIds.containsAll(requiredIds)) {
                // Calculate discount amount
                BigDecimal totalPrice = calculateTotalPriceForProducts(cartItems, requiredIds);
                BigDecimal discountAmount = totalPrice.multiply(
                        BigDecimal.valueOf(rule.getDiscountPercentage() / 100.0));
                
                // Create and save discount application
                DiscountApplication application = new DiscountApplication(cart, rule, discountAmount);
                applications.add(discountApplicationRepository.save(application));
            }
        }
        
        return applications;
    }
    
    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountApplicationRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Discount application not found"));
    }
    
    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
    
    private Set<Long> parseProductIds(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return Collections.emptySet();
        }
        
        return Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .collect(Collectors.toSet());
    }
    
    private BigDecimal calculateTotalPriceForProducts(List<CartItem> items, Set<Long> productIds) {
        return items.stream()
                .filter(item -> productIds.contains(item.getProduct().getId()))
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}