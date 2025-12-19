package com.example.demo.service.impl;

import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository itemRepo;

    public CartItemServiceImpl(CartItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public CartItem addItem(CartItem cartItem) {
        return itemRepo.save(cartItem);
    }

    @Override
    public List<CartItem> getItemsByCartId(Long cartId) {
        return itemRepo.findByCartId(cartId);
    }

    @Override
    public void removeItem(Long id) {
        itemRepo.deleteById(id);
    }
}
