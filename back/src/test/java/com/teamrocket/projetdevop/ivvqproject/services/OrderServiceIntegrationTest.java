package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.repositories.OrderRepository;
import com.teamrocket.projetdevop.ivvqproject.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceIntegrationTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    void setup()
    {
        this.order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(10),"confirmé");
    }

    @Test
    public void testSavedOrderHasId(){

        // given: une commande non persisté product
        // when: une commande est persisté
        orderRepository.save(order);
        // then: order a un id
        assertNotNull(order.getOrderId());
    }

    @Test
    public void testFetchedOrderIsNullAtTheBeginning() {
        // given: une order persisté produit
        orderRepository.save(order);
        // when: on appelle findOne avec l'id de cette order
        Order fetched = orderRepository.findByOrderId(order.getOrderId());
        // then: le résultat n'est pas null
        assertNull(fetched);
    }


    @Test
    public void testFindOrderWithUnexistingId() {
        // when:  findByOrderId est appelé avec un id ne correspondant à aucun objet en base
        // then: null est retourné
        assertNull(orderRepository.findByOrderId(100L));
    }

}
