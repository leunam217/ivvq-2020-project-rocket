package com.teamrocket.projetdevop.ivvqproject.model;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductOrderedTest {

    private static Validator validator;

    @BeforeAll
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void product_id_empty()
    {
        final ProductOrdered product = new ProductOrdered(
                new Product("", "coucou", new BigDecimal(123), 100,"desc","icon"),
                3);
        assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    void product_name_empty()
    {
        final ProductOrdered product = new ProductOrdered(
                new Product("B001", "", new BigDecimal(123), 100,"desc","icon"),
                3);

        assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    void product_description_empty()
    {
        final ProductOrdered product = new ProductOrdered(
                new Product("B001", "coucou", new BigDecimal(123), 100,"","icon"),
                3);

        assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    void product_price_negatif()
    {
        final ProductOrdered product = new ProductOrdered(
                new Product("B001", "coucou", new BigDecimal(123), -100,"desc","icon"),
                3);

        assertFalse(validator.validate(product).isEmpty());
    }

    @Test
    void get_product_id()
    {
        final ProductOrdered product = new ProductOrdered(
                new Product("B001", "coucou", new BigDecimal(123), 100,"desc","icon"),
                3);
        assert(product.getProductId().equals("B001"));
    }

    @Test
    void get_product_name()
    {
        final ProductOrdered product = new ProductOrdered(
                new Product("B001", "coucou", new BigDecimal(123), 100,"desc","icon"),
                3);
        assert(product.getProductName().equals("coucou"));
    }

    @Test
    void get_product_price()
    {

        final ProductOrdered product = new ProductOrdered(
                new Product("B01", "coucou", new BigDecimal(123), 100,"desc","icon"),
                3);
        assert(product.getProductPrice().equals(new BigDecimal(123)));
    }

    @Test
    void get_product_stock()
    {
        final ProductOrdered product = new ProductOrdered(
                new Product("B01", "coucou", new BigDecimal(123), 100,"desc","icon"),
                3);
        assert(product.getProductStock().equals(100));
    }

    @Test
    void get_product_description()
    {
        final ProductOrdered product = new ProductOrdered(
                new Product("B01", "coucou", new BigDecimal(123), 100,"desc","icon"),
                3);
        assert(product.getProductDescription().equals("desc"));
    }

    @Test
    void test_not_equals() {
        final ProductOrdered product = new ProductOrdered(

                new Product("B01", "coucou", new BigDecimal(123), 100,"desc","icon"),
                2);
        final ProductOrdered product1 = new ProductOrdered(
                new Product("B01", "coucou", new BigDecimal(123), 100,"desc","icon"),
                3);

        Assertions.assertNotSame(product, product1);
    }

    @Test
    void hashcode_not_equals()
    {
        final ProductOrdered product = new ProductOrdered(

                new Product("B01", "coucou", new BigDecimal(123), 100,"desc","icon"),
                2);
        final ProductOrdered product1 = new ProductOrdered(
                new Product("B01", "coucou", new BigDecimal(123), 100,"desc","icon"),
                3);
        Assertions.assertNotSame(product, product1);

    }


}
