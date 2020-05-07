package com.teamrocket.projetdevop.ivvqproject.controller;

import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import com.teamrocket.projetdevop.ivvqproject.services.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/orders"})
public class OrderController {

    Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable long id) {
        Optional<Order> order = orderService.findById(id);

        if (!order.isPresent()) {
            log.info("Order with id " + id + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order.get());
    }

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrUpdate(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable long id, @Valid @RequestBody Order order) {
        Optional<Order> tempOrder = orderService.findById(id);

        if (!tempOrder.isPresent()) {
            log.error("Order with id " + id + " does not exist for updating.");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(orderService.createOrUpdate(order));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        Optional<Order> tempOrder = orderService.findById(id);

        if (!tempOrder.isPresent()) {
            log.error("Order with id " + id + " does not exist for deletion.");
            return;
        }

        orderService.deleteById(id);
    }
}

