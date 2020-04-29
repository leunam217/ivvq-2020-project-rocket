package com.teamrocket.projetdevop.ivvqproject.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Order implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    @NotEmpty
    private String buyerName;

    @NotEmpty
    private String buyerEmail;

    @NotEmpty
    private String buyerAddress;

    @NotEmpty
    private String buyerPhone;

    @NotNull
    private BigDecimal orderAmount;

    @NotNull
    private Integer orderStatus;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Order() {}

    public Order(User buyer) {
        this.buyerEmail = buyer.getEmail();
        this.buyerName = buyer.getName();
        this.buyerAddress = buyer.getAddress();
        this.buyerPhone = buyer.getPhoneNumber();
        this.orderAmount = buyer.getCart().getProducts().stream().map(item -> item.getProductPrice().multiply(new BigDecimal(item.getCount())))
                    .reduce(BigDecimal::add)
                    .orElse(new BigDecimal(0));
         this.orderStatus = 0;

    }


}
