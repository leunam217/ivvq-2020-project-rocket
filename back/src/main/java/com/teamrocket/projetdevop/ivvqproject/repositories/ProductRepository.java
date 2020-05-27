package com.teamrocket.projetdevop.ivvqproject.repositories;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByProductId(String id);

    List<Product> findAllByProductNameContaining(String productName);

    List<Product> findAllByOrderByProductId();
}
