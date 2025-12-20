package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount_applications")
public class DiscountApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bundle_rule_id", nullable = false)
    private BundleRule bundleRule;
    
    @Column(name = "discount_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountAmount;
    
    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt;
    
    @PrePersist
    protected void onCreate() {
        appliedAt = LocalDateTime.now();
    }
    
    // Constructors
    public DiscountApplication() {
    }
    
    public DiscountApplication(Cart cart, BundleRule bundleRule, BigDecimal discountAmount) {
        this.cart = cart;
        this.bundleRule = bundleRule;
        this.discountAmount = discountAmount;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Cart getCart() {
        return cart;
    }
    
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    public BundleRule getBundleRule() {
        return bundleRule;
    }
    
    public void setBundleRule(BundleRule bundleRule) {
        this.bundleRule = bundleRule;
    }
    
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }
    
    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}