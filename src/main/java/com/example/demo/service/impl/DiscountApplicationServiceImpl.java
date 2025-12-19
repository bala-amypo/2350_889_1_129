package com.example.demo.service;

import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.DiscountApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountApplicationServiceImpl implements DiscountApplicationService {

    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountApplicationServiceImpl(
            DiscountApplicationRepository discountApplicationRepository) {
        this.discountApplicationRepository = discountApplicationRepository;
    }

    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}
