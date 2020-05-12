package com.teamrocket.projetdevop.ivvqproject.controller;


import com.teamrocket.projetdevop.ivvqproject.domain.*;

import com.teamrocket.projetdevop.ivvqproject.repositories.ProductInOrderRepository;
import com.teamrocket.projetdevop.ivvqproject.service.ShoppingCartService;

import com.teamrocket.projetdevop.ivvqproject.service.ProductOrderedService;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;


@CrossOrigin
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductOrderedService productOrderedService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @PostMapping("")
    public ResponseEntity<ShoppingCart> finalCart(@RequestBody Collection<ProductOrdered> productInOrders, Principal principal) {
        User user = userService.findOne(principal.getName());
        try {
            shoppingCartService.finalCart(productInOrders, user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Failed");
        }
        return ResponseEntity.ok(shoppingCartService.getCart(user));
    }

    @GetMapping("")
    public ShoppingCart getCart(Principal principal) {
        User user = userService.findOne(principal.getName());
        return shoppingCartService.getCart(user);
    }


    @PostMapping("/add")
    public boolean addItemToCart(@RequestBody ItemForm form, Principal principal) {
        Product productInfo = productService.findOne(form.getProductId());
        try {
            finalCart(Collections.singleton(new ProductOrdered(productInfo, form.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/{itemId}")
    public ProductOrdered modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        User user = userService.findOne(principal.getName());
        productOrderedService.update(itemId, quantity, user);
        return productOrderedService.findOne(itemId, user);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        User user = userService.findOne(principal.getName());
        shoppingCartService.delete(itemId, user);
    }


    @PostMapping("/checkout")
    public ResponseEntity checkoutCart(Principal principal) {
        User user = userService.findOne(principal.getName());
        shoppingCartService.placeOrder(user);
        return ResponseEntity.ok(null);
    }


}
