package com.teamrocket.projetdevop.ivvqproject.service;


import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;

import java.util.Collection;


public interface ShoppingCartService {

    ShoppingCart getCart(User user);

    void finalCart(Collection<ProductOrdered> productInOrders, User user);

    void delete(String itemId, User user);


    void placeOrder(User user);
}
