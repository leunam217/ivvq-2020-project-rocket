package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl {

    @Autowired
    ProductRepository productRepository;

    /**
     * method to find one product
     * @param productId
     * @return
     */

    public Optional<Product> findOneProductById(Long productId) {
       return productRepository.findById(productId);

    }


    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    /**
     * method to increase product stock
     * @param productId
     * @param amount
     * @throws Exception
     */


  public void increaseProductStock(Long productId, int amount)  {

        Product product = findOneProductById(productId).get();
        if(product == null) {
            throw new IllegalArgumentException("Product doest not exist");
        }

        int updateProduct = product.getProductStock() + amount;

        if (updateProduct <= 0) {
            throw new IllegalArgumentException("Product is not enough");
        }
        product.setProductStock(updateProduct);
        productRepository.save(product);

    }

    /**
     * method to decrease product stock
     * @param productId
     * @param amount
     * @throws Exception
     */


    public void decreaseProductStock(Long productId, int amount)  {
        Product product = findOneProductById(productId).get();
        if (product == null) {
            throw new IllegalArgumentException("Product doest not exist");
        }

        int updateProduct = product.getProductStock() - amount;
        if(updateProduct == 0) {
            throw new IllegalArgumentException("Product is not enough");
        }

        product.setProductStock(updateProduct);
        productRepository.save(product);
    }

    /**
     * method to save new product
     * @param product
     * @return
     */

    @Transactional
    public Product createOrUpdate(Product product) {
        if (product == null)
        {
            throw new IllegalArgumentException("Product can't be null");
        }
        return productRepository.save(product);
    }

    /**
     * method to delete one selected product
     * @param productId
     */

    @Transactional
    public void deleteProduct(Long productId) {

        Product product = findOneProductById(productId).get();
        if(product == null) {
            throw new IllegalArgumentException("Product doesn't exist");
        }
        productRepository.delete(product);
    }

}
