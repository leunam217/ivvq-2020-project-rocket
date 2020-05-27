package com.teamrocket.projetdevop.ivvqproject.domain;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ItemForm {

    @Min(value = 1)
    private Integer quantity;

    @NotEmpty
    private String productId;

    public ItemForm(Integer quantity, String productId)
    {
        this.quantity = quantity;
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

}