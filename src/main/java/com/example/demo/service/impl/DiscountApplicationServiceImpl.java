package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.service.DiscountApplicationService;
import org.springframework.stereotype.Service;

@Service
public class DiscountApplicationServiceImpl implements DiscountApplicationService {

    @Override
    public Cart applyDiscounts(Long cartId) {
        return new Cart();
    }

    @Override
    public Cart getDiscounts(Long cartId) {
        return new Cart();
    }
}
