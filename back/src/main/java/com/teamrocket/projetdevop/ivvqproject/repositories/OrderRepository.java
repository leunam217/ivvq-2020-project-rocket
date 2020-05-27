package com.teamrocket.projetdevop.ivvqproject.repositories;

import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderId(Long orderId);

    List<Order> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail);

    List<Order> findAllByOrderByOrderStatusAscCreateTimeDesc();

}
