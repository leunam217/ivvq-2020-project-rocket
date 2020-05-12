package com.teamrocket.projetdevop.ivvqproject.controller;


import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/product")
    public List<Product> findAllProduct() {

        return productService.findAll();
    }

    @GetMapping("/product/{productId}")
    public Product showOneProduct(@PathVariable("productId") String productId) {

        Product productInfo = productService.findOne(productId);

        return productInfo;
    }

    @PostMapping("/seller/product/new")
    public ResponseEntity createProduct(@Valid @RequestBody Product product,
                                 BindingResult bindingResult) {
        Product productIdExists = productService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult.rejectValue("productId", "error.product",
                            "Product already exist");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/seller/product/{id}/edit")
    public ResponseEntity editProduct(@PathVariable("id") String productId,
                               @Valid @RequestBody Product product,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/seller/product/{id}/delete")
    public ResponseEntity deleteProduct(@PathVariable("id") String productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

}
