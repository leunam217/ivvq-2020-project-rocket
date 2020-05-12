package com.teamrocket.projetdevop.ivvqproject.modeltests;

/**import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;

public class ProductTest {

    private static Validator validator;

    @BeforeAll
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator =  factory.getValidator();
    }

    @Test
    public void productNameIsNullTest()
    {
        Product product = new Product(null,"Un aspirateur moderne",new BigDecimal(432),23,"img");
        Assertions.assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    public void productNameIsEmptyTest()
    {
        Product product = new Product("","Un aspirateur moderne",new BigDecimal(432),23,"img");
        Assertions.assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    public void productDescriptionIsNullTest()
    {
        Product product = new Product("aspirateur",null,new BigDecimal(432),23,"img");
        Assertions.assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    public void productDescriptionIsEmptyTest()
    {
        Product product = new Product("aspirateur","",new BigDecimal(432),23,"img");
        Assertions.assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    public void productPriceIsNullTest()
    {
        Product product = new Product("aspirateur","un aspirateur moderne",null,23,"img");
        Assertions.assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    public void productPriceIsNegativeTest()
    {
        Product product = new Product("aspirateur","un aspirateur moderne",new BigDecimal(-12),23,"img");
        Assertions.assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    public void productStockIsNullTest()
    {
        Product product = new Product("aspirateur","un aspirateur moderne",new BigDecimal(12),null,"img");
        Assertions.assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    public void productStockIsNegativeTest()
    {
        Product product = new Product("aspirateur","un aspirateur moderne",new BigDecimal(12),-24,"img");
        Assertions.assertFalse(validator.validate(product).isEmpty());
    }

}
**/