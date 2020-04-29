package com.teamrocket.projetdevop.ivvqproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    ShoppingCart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @NotNull(message = "is required")
    @Size(min = 3, max = 50, message = "provide at least 3 and not more than 50 characters")
    private String productName;

    @NotNull(message = "is required")
    @Min(value = 3,  message = "give at least a short description of the product")
    private String productDescription;

    @NotNull(message = "provide a valid price")
    @Min(value = 0, message = "cannot be a negative number")
    private BigDecimal productPrice;

   @NotNull(message = "provide a valid quantity of stock")
   @Min(value = 0, message = "a stock cannot be negative")
    private Integer productStock;

   @Min(value = 1)
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                productName.equals(product.productName) &&
                productDescription.equals(product.productDescription) &&
                productPrice.equals(product.productPrice) &&
                Objects.equals(productStock, product.productStock) &&
                count.equals(product.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, productDescription, productPrice, productStock, count);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", count=" + count +
                '}';
    }

    public Product() {}

    public Product(String productName, String productDescription, BigDecimal productPrice, Integer productStock, Integer quantity)
    {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.count = quantity;
    }
}
