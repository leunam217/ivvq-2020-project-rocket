package com.teamrocket.projetdevop.ivvqproject.service;


import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.User;

import java.util.List;


public interface ProductService {

    Product findOne(String productId);
    List<Product> findAll();

    List<Product> findAllByName(String productName);

    void increaseStock(String productId, int amount);

    void decreaseStock(String productId, int amount);

    Product update(Product productInfo);
    Product save(Product productInfo);

    void delete(String productId);


}
