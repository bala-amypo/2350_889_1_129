package com.example.demo.service;

import com.example.demo.model.DiscountApplication;
import java.util.List;

public interface DiscountApplicationService {

    List<DiscountApplication> getDiscountsByCart(Long cartId);
}
