package com.teamrocket.projetdevop.ivvqproject.controller;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.services.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/products")
public class ProductController {

    Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductServiceImpl productService;

    @GetMapping
    public List<Product> findAll()
    {
        return productService.findAllProduct();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product)
    {
        return ResponseEntity.ok(productService.createOrUpdate(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {

        Optional<Product> product = productService.findOneProductById(id);

        if (!product.isPresent()) {
            log.info("Product with id " + id + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product.get());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Product> update(@PathVariable long id, @Valid @RequestBody Product product) {

        Optional<Product> p = productService.findOneProductById(id);

        if (!p.isPresent()) {
            log.error("Product with id " + id + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        p.get().setProductName(product.getProductName());
        p.get().setProductPrice(product.getProductPrice());
        p.get().setProductDescription(product.getProductDescription());
        p.get().setProductStock(product.getProductStock());
        p.get().setCount(product.getCount());
        p.get().setProductImage(product.getProductImage());

        return ResponseEntity.ok(productService.createOrUpdate(p.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        Optional<Product> p = productService.findOneProductById(id);

        if (!p.isPresent()) {
            log.error("Product with id " + id + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
