package com.teamrocket.projetdevop.ivvqproject.service;


import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.domain.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductOrdered findOne(String itemId, User user);
}
