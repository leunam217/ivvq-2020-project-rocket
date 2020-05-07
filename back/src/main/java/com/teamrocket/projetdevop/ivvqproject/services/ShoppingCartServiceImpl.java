package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.OrderRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartServiceImpl {
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;


    /**
     * method to delete product in shopping cart
     * @param productId
     * @param user
     */

    public void deleteCart(Long productId, User user) {
        Optional<Product> product = user.getCart().getProducts().stream().filter(e -> productId.equals(e.getId())).findFirst();
        product.ifPresent(product1 -> {
           product1.setCart(null);
           productService.deleteProduct(product1.getId());
       });
    }



}
