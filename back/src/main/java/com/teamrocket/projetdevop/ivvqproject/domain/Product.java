package com.teamrocket.projetdevop.ivvqproject.domain;

import lombok.Data;

import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;



@Entity
@Data
@DynamicUpdate
public class Product implements Serializable {

    @Id
    private String productId;


    @NotEmpty
    private String productName;



    @Min(0)
    private BigDecimal productPrice;



    @Min(0)
    private Integer productStock;


    @NotEmpty
    private String productDescription;


    private String productIcon;

    public Product(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription)
    {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
    }
    public Product() {
    }

    public String getProductId() {
        return productId;
    }


    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }


}
