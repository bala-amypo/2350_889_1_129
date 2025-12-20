package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bundle_rules")
public class BundleRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "rule_name", unique = true, nullable = false)
    private String ruleName;
    
    @Column(name = "required_product_ids", nullable = false)
    private String requiredProductIds; // CSV format: "1,2,3"
    
    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    // Constructors
    public BundleRule() {
    }
    
    public BundleRule(String ruleName, String requiredProductIds, Double discountPercentage) {
        this.ruleName = ruleName;
        this.requiredProductIds = requiredProductIds;
        this.discountPercentage = discountPercentage;
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRuleName() {
        return ruleName;
    }
    
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    
    public String getRequiredProductIds() {
        return requiredProductIds;
    }
    
    public void setRequiredProductIds(String requiredProductIds) {
        this.requiredProductIds = requiredProductIds;
    }
    
    public Double getDiscountPercentage() {
        return discountPercentage;
    }
    
    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
}