package com.teamrocket.projetdevop.ivvqproject.service.impl;


import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.repositories.OrderRepository;

import com.teamrocket.projetdevop.ivvqproject.repositories.ProductInOrderRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.ProductRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import com.teamrocket.projetdevop.ivvqproject.service.OrderService;

import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllByOrderByOrderStatusAscCreateTimeDesc();
    }



    @Override
    public List<Order> findByBuyerEmail(String email) {
        return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email);
    }

    @Override
    public Order findOne(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if(order == null) {
            throw new IllegalArgumentException("ORDER_NOT_FOUND");
        }
        return order;
    }

    @Override
    @Transactional
    public Order finish(Long orderId) {
        Order order = findOne(orderId);


        order.setOrderStatus("FINISHED");
        orderRepository.save(order);
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public Order cancel(Long orderId) {
        Order orderMain = findOne(orderId);

        orderMain.setOrderStatus("CANCELED");
        orderRepository.save(orderMain);

        // Restore Stock
        Iterable<ProductOrdered> products = orderMain.getProducts();
        for(ProductOrdered productInOrder : products) {
            Product productInfo = productRepository.findByProductId(productInOrder.getProductId());
            if(productInfo != null) {
                productService.increaseStock(productInOrder.getProductId(), productInOrder.getCount());
            }
        }
        return orderRepository.findByOrderId(orderId);

    }
}
