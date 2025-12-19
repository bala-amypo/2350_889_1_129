package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔥 THIS WAS MISSING OR WRONG
public class DiscountServiceImpl implements DiscountService {

    private final CartRepository cartRepository;
    private final DiscountApplicationRepository discountRepository;

    public DiscountServiceImpl(
            CartRepository cartRepository,
            DiscountApplicationRepository discountRepository) {
        this.cartRepository = cartRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    public DiscountApplication applyDiscount(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(EntityNotFoundException::new);

        DiscountApplication discount = new DiscountApplication();
        discount.setCart(cart);
        discount.setDiscountAmount(0.0); // placeholder logic

        return discountRepository.save(discount);
    }

    @Override
    public List<DiscountApplication> getDiscountsForCart(Long cartId) {
        return discountRepository.findByCartId(cartId);
    }
}
