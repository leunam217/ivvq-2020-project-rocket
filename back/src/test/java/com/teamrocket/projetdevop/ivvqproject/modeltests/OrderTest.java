package com.teamrocket.projetdevop.ivvqproject.modeltests;


/**import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Date;


public class OrderTest {

    private static Validator validator;

    @BeforeAll
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator =  factory.getValidator();
    }

    @Test
    public void orderAmountIsNull()
    {
        Order order = new Order(null,"Confirmé",new Date(), new Date());
        Assertions.assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    public void orderStatusIsNull()
    {
        Order order = new Order(new BigDecimal(123),null,new Date(), new Date());
        Assertions.assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    public void orderStatusIsEmpty()
    {
        Order order = new Order(new BigDecimal(123),"",new Date(), new Date());
        Assertions.assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    public void orderDateCreateIsNull()
    {
        Order order = new Order(new BigDecimal(123),"en cours",null, new Date());
        Assertions.assertFalse(validator.validate(order).isEmpty());
    }

    @Test
    public void orderDateUpdateIsNull()
    {
        Order order = new Order(new BigDecimal(123),"en cours",new Date(), null);
        Assertions.assertFalse(validator.validate(order).isEmpty());
    }
}
**/