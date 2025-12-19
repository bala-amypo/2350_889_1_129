package com.example.demo.service;

import com.example.demo.model.DiscountApplication;

import java.util.List;

public interface DiscountService {

    DiscountApplication applyDiscount(Long cartId);

    List<DiscountApplication> getDiscountsForCart(Long cartId);
}
