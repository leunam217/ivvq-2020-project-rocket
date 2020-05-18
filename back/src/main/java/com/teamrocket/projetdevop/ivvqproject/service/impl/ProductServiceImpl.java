package com.teamrocket.projetdevop.ivvqproject.service.impl;


import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.repositories.ProductRepository;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productInfoRepository;

    @Override
    public Product findOne(String productId) {

        Product product = productInfoRepository.findByProductId(productId);
        return product;
    }



    @Override
    public List<Product> findAll() {
        return productInfoRepository.findAllByOrderByProductId();
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productInfoRepository.findAllByProductNameContaining(name);
    }

    @Override
    @Transactional
    public void increaseStock(String productId, int amount) {
        Product productInfo = findOne(productId);
        if (productInfo == null) throw new IllegalArgumentException("PRODUCT_NOT_EXIST");

        int update = productInfo.getProductStock() + amount;
        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(String productId, int amount) {
        Product productInfo = findOne(productId);
        if (productInfo == null) throw new IllegalArgumentException("PRODUCT_NOT_EXIST");

        int update = productInfo.getProductStock() - amount;
        if(update <= 0) throw new IllegalArgumentException("PRODUCT_NOT_ENOUGH");

        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }




    @Override
    public Product update(Product product) {

        productInfoRepository.findByProductId(product.getProductId());
        return productInfoRepository.save(product);
    }

    @Override
    public Product save(Product product) {
        return update(product);
    }

    @Override
    public void delete(String productId) {
        Product productInfo = findOne(productId);
        if (productInfo == null) throw new IllegalArgumentException("PRODUCT_NOT_EXIST");
        productInfoRepository.delete(productInfo);

    }

}
