package com.teamrocket.projetdevop.ivvqproject.model;

import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {

    private static Validator validator;

    @BeforeAll
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void order_id_empty()
    {
        final Order order = new Order(null, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(200), "confirmé");
        assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    void buyerEmail_empty()
    {
        final Order order = new Order(1L, "", "bob","1234","toulouse",new BigDecimal(200),"confirmé");
        assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    void buyerName_empty()
    {
        final Order order = new Order(1L, "bob@email.com", "","1234","toulouse",new BigDecimal(200),"confirmé");
        assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    void buyerPhone_empty()
    {
        final Order order = new Order(1L, "bob@email.com", "Bob","","toulouse",new BigDecimal(200),"confirmé");
        assertFalse(validator.validate(order).isEmpty());
    }
    @Test
    void buyerAddress_empty()
    {
        final Order order = new Order(1L, "bob@email.com", "bobo","1234","",
                new BigDecimal(200),"confirmé");
        assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    void orderAmount_isNegative()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(-1),"confirmé");
        assertFalse(validator.validate(order).isEmpty());
    }
    @Test
    void orderAmount_isNull()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(0),"confirmé");
        assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    void get_order_id()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(10),"confirmé");
        assert(order.getOrderId().equals(1L));
    }
    @Test
    void get_order_status()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(10),"confirmé");
        assert(order.getOrderStatus().equals("confirmé"));
    }

    @Test
    void get_order_amount()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(10),"confirmé");
        assert(order.getOrderAmount().equals(new BigDecimal(10)));
    }

    @Test
    void get_buyer_email()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(10),"confirmé");
        assert(order.getBuyerEmail().equals("bob@email.com"));
    }
    @Test
    void get_buyer_name()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(10),"confirmé");
        assert(order.getBuyerName().equals("bob"));
    }
    @Test
    void get_buyer_phone()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(10),"confirmé");
        assert(order.getBuyerPhone().equals("1234"));
    }

    @Test
    void get_buyer_address()
    {
        final Order order = new Order(1L, "bob@email.com", "bob","1234","toulouse",
                new BigDecimal(10),"confirmé");
        assert(order.getBuyerAddress().equals("toulouse"));
    }



}
