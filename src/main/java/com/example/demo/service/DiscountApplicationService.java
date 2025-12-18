package com.example.demo.service;

import com.example.demo.model.DiscountApplication;
import java.util.List;

public interface DiscountApplicationService {
    void applyDiscounts(Long cartId);
    List<DiscountApplication> getDiscounts(Long cartId);
}
