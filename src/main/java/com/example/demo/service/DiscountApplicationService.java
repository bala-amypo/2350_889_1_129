package com.example.demo.service;

import com.example.demo.model.Cart;

public interface DiscountApplicationService {
    Cart applyDiscounts(Long cartId);
    Cart getDiscounts(Long cartId);
}
