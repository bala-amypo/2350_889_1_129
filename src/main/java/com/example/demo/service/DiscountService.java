package com.example.demo.service;

import com.example.demo.model.DiscountApplication;
import java.util.List;

public interface DiscountService {

    void evaluateDiscounts(Long cartId);

    DiscountApplication getApplicationById(Long id);

    List<DiscountApplication> getApplicationsForCart(Long cartId);
}
