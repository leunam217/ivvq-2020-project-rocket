package com.teamrocket.projetdevop.ivvqproject.services;
/**

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.OrderRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.ProductInOrderRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CartServiceIntegrationTest {

    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;
    @Autowired
    ShoppingCartRepository cartRepository;
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    ShoppingCart shoppingCart;
    User user;

    Collection<ProductOrdered> productOrdereds = new HashSet<>();

    @BeforeEach
    void setup()
    {
        this.user = new User("bob@email.com",passwordEncoder.encode("secret"),"Bob","21345","Toulouse");
        this.shoppingCart = new ShoppingCart(user);

        productOrdereds.add(new ProductOrdered(new Product("B01", "coucou", new BigDecimal(123), 100,"desc"),
                2));
    }




}
**/