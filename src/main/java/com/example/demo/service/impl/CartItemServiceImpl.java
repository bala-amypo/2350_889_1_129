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
    public CartItem addItem(CartItem item) {
        return itemRepo.save(item);
    }

    @Override
    public List<CartItem> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public void deleteItem(Long id) {
        itemRepo.deleteById(id);
    }
}
