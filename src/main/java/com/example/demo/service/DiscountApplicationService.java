package com.example.demo.service;

import com.example.demo.model.DiscountApplication;
import java.util.List;

public interface DiscountApplicationService {

    DiscountApplication getApplicationById(Long id);

    List<DiscountApplication> getApplicationsForCart(Long cartId);
}
