package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import com.teamrocket.projetdevop.ivvqproject.repositories.OrderRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.ProductRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrUpdate(Order order){
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
