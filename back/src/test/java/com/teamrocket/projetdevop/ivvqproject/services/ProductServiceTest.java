/**package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class ProductServiceTest {

    @Autowired
    ProductServiceImpl productService;



    @Test
    @Disabled
    public void findOneProduct()
    {
        Product product = productService.findOneProductById(1L);

    }

    @Test
   public void increaseStock() {
        Integer old = productService.findOneProductById(16L).getProductStock();
        productService.increaseProductStock(16L, 50);
        Integer added = productService.findOneProductById(16L).getProductStock();
        Assert.assertEquals(50, added - old);
    }

}

 **/