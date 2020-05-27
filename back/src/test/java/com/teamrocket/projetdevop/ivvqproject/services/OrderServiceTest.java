package com.teamrocket.projetdevop.ivvqproject.services;


import com.teamrocket.projetdevop.ivvqproject.domain.Order;

import com.teamrocket.projetdevop.ivvqproject.repositories.OrderRepository;
import com.teamrocket.projetdevop.ivvqproject.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.BDDMockito.given;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderServiceTest {

    private static Validator validator;
        @Mock
        OrderRepository orderRepository;

        @InjectMocks
        OrderServiceImpl orderServiceImpl;


        @BeforeAll
        public static void setupContext() {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }


        @Test
        void should_return_findAll()
        {
            List<Order> orders = new ArrayList<>();

            given(orderRepository.findAll()).willReturn(orders);

            List<Order> expectedProducts = orderServiceImpl.findAll();
            Assertions.assertEquals(expectedProducts,orders);

        }

        @Test
        void should_return_findOne()
        {
            Long id = 1L;

            final Order order = new Order();

            given(orderRepository.findByOrderId(id)).willReturn(order);

            final Order expectedOrder = orderServiceImpl.findOne(id);
            assertThat(expectedOrder).isNotNull();

        }

        @Test
        void order_null()
        {
            Long orderId = null;
            given(orderRepository.findByOrderId(orderId)).willReturn(null);

            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               orderServiceImpl.findOne(orderId);
            });
           // Assertions.assertEquals("ORDER_NOT_FOUND", exception.getMessage());
        }


        @Test
        void should_return_findByEmail()
        {
            String email = "toto@email.com";

            final List<Order> order = new ArrayList<>();

            given(orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email)).willReturn(order);

            final List<Order> expectedOrder = orderServiceImpl.findByBuyerEmail(email);

            assertThat(expectedOrder).isNotNull();


        }

        @Test

        void should_return_finish()
        {
            Long id = 1L;

            final Order order = new Order();

            given(orderRepository.findByOrderId(id)).willReturn(order);
            final Order expectedOrder = orderServiceImpl.finish(id);
            Assertions.assertEquals(expectedOrder.getOrderStatus(), order.getOrderStatus());


        }

        @Test
        void should_return_cancel()
        {
            Long id = 1L;
            final Order order = new Order();

            given(orderRepository.findByOrderId(id)).willReturn(order);

            final Order expectedOrder = orderServiceImpl.cancel(id);
            Assertions.assertEquals(expectedOrder.getOrderStatus(), order.getOrderStatus());


        }



}
