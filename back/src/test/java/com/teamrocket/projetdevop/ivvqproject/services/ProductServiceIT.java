package com.teamrocket.projetdevop.ivvqproject.services;


import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ProductServiceIT {

    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setup()
    {
        this.product = new Product("B001", "Rocket", new BigDecimal(123), 100,"desc","icon");
    }

    @Test
    public void testSavedProductHasId(){
        // given: un produit non persisté product
        // when: product est persisté
        productService.save(product);
        // then: product a un id
        assertNotNull(product.getProductId());
    }


    @Test
    public void testFetchedProductIsNotNull() {
        // given: une produit persisté produit
        productService.save(product);
        // when: on appelle findOne avec l'id de ce produit
        Product fetched = productService.findOne(product.getProductId());
        // then: le résultat n'est pas null
        assertNotNull(fetched);
    }

    @Test
    public void testFetchedProductIsUnchangedForName() {
        // given: un produit persisté produit
        productService.save(product);
        // when: on appelle findOne avec l'id de ce produit
        Product fetched = productService.findOne(product.getProductId());
        // then: l'Activite obtenue en retour a le bon id
        assertEquals(fetched.getProductId(), product.getProductId());
        // then : le produit obtenue en retour a le bon nom
        assertEquals(fetched.getProductName(), product.getProductName());
    }

    @Test
    @Transactional
    public void testUpdatedProductIsUpdated() {
        // given: un produit persisté product
        productService.save(product);

        Product fetched = productService.findOne(product.getProductId());
        // when: le stock est modifié au niveau "objet"
        fetched.setProductStock(210);
        // when: l'objet product est mis à jour en base
        productService.save(fetched);
        // when: l'objet product est relu en base
        Product fetchedUpdated = productService.findOne(product.getProductId());
        // then: le stock a bien été mis à jour
        assertEquals(fetched.getProductStock(), fetchedUpdated.getProductStock());
    }

    @Test
    public void testFindProductWithUnexistingId() {
        // when:  findOne est appelé avec un id ne correspondant à aucun objet en base
        // then: null est retourné
        assertNull(productService.findOne("100L"));
    }

    @Test
    @Transactional
    public void testDeleteProduct()
    {
        String productId = "20";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            productService.delete(productId);
            assertNull(product);
        });

    }

    @Test
    public void testFindAllProductFromDataLoaderCardinal() {
        // given: un DataLoader initialisant la base à 6 produits
        // when: la liste des produits est récupérée
        List<Product> products = productService.findAll();
        // then: il y a 6 activités dedans
        assertEquals(6, products.size());
    }


}
