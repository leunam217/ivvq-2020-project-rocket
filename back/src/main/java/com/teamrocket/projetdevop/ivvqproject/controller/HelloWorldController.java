package com.teamrocket.projetdevop.ivvqproject.controller;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;

import com.teamrocket.projetdevop.ivvqproject.services.OrderServiceImpl;
import com.teamrocket.projetdevop.ivvqproject.services.ProductServiceImpl;
import com.teamrocket.projetdevop.ivvqproject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HelloWorldController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/testSaveOnPostgresql", method = RequestMethod.GET)
    public String createUser(){
       // userService.saveUser(new User("Marieme","mk@gm.cm","myPass", "Toulouse","073332","customer"));
       productService.saveProduct(new Product("tata", "description1",new BigDecimal("1324"), 5, 1));

        return "product is created";
    }

}