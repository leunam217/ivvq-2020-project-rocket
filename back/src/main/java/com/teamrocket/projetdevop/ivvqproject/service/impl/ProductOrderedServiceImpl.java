package com.teamrocket.projetdevop.ivvqproject.service.impl;


import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.ProductInOrderRepository;
import com.teamrocket.projetdevop.ivvqproject.service.ProductInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    @Transactional
    public void update(String itemId, Integer quantity, User user) {
        Optional<ProductOrdered> op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            productInOrderRepository.save(productInOrder);
        });

    }

    @Override
    public ProductOrdered findOne(String itemId, User user) {
        Optional<ProductOrdered> op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<ProductOrdered> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }
}

