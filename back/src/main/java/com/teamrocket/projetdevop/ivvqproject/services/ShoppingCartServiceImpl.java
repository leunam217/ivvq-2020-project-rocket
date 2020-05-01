package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.OrderRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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

    /**
     * method to check out cart
     * @param user
     */
    public void checkoutCart(User user) {
        Order order = new Order(user);
        orderRepository.save(order);
        user.getCart().getProducts().forEach(product -> {
            product.setCart(null);
            product.setOrder(order);

            productService.decreaseProductStock(product.getId(), product.getCount());
            productService.saveProduct(product);
        });
    }

}
