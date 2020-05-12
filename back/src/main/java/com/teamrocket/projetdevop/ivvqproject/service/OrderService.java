package com.teamrocket.projetdevop.ivvqproject.service;


import com.teamrocket.projetdevop.ivvqproject.domain.Order;

import java.util.List;


public interface OrderService {
    List<Order> findAll();

    List<Order> findByBuyerEmail(String email);

    Order findOne(Long orderId);


    Order finish(Long orderId);

    Order cancel(Long orderId);

}
