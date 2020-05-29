package com.teamrocket.projetdevop.ivvqproject.utils;

import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import com.teamrocket.projetdevop.ivvqproject.util.DataLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class DataLoaderIntegrationTest {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    DataLoader dataLoader;

    @Test
    public void testCardinalProducts() {
        // au démarrage de l'application, il y a 6 produits en base
        assertEquals(6, productService.findAll().size());
    }

    @Test
    public void testCheckRocket3() {
        // le produit Rocket3 est en base
        assertEquals("Rocket3000", dataLoader.getRocket3().getProductName());
    }

    @Test
    public void testCheckRocket4() {
        // le produit Rocket4 est en base
        assertEquals("Rocket4000", dataLoader.getRocket4().getProductName());
    }

    @Test
    public void testCheckRocket5() {
        // le produit Rocket5 est en base
        assertEquals("Rocket5000", dataLoader.getRocket5().getProductName());
    }

    @Test
    public void testCheckRocket6() {
        // le produit Rocket6 est en base
        assertEquals("Rocket60000", dataLoader.getRocket6().getProductName());
    }

}
