package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bundle_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
public class BundleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private String requiredProducts; // CSV "1,2,3"

    @Column(nullable = false)
    private Double discountPercentage;

    private Boolean active = true;

    @PrePersist
    @PreUpdate
    public void validateDiscount() {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new RuntimeException("Invalid discount");
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getRequiredProducts() { return requiredProducts; }
    public void setRequiredProducts(String requiredProducts) {
        this.requiredProducts = requiredProducts;
    }

    public Double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}