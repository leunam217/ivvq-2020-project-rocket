package com.teamrocket.projetdevop.ivvqproject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "orders")
@Data
@DynamicUpdate
public class Order implements Serializable {
	private static final long serialVersionUID = -3819883511505235030L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
	private Set<ProductOrdered> products = new HashSet<>();

	@NotEmpty
	private String buyerEmail;

	@NotEmpty
	private String buyerName;

	@NotEmpty
	private String buyerPhone;

	@NotEmpty
	private String buyerAddress;

	@Min(10)
	private BigDecimal orderAmount;

	private String orderStatus;

	@CreationTimestamp
	private LocalDateTime createTime;

	@UpdateTimestamp
	private LocalDateTime updateTime;

	public Order(User buyer) {
		this.buyerEmail = buyer.getEmail();
		this.buyerName = buyer.getName();
		this.buyerPhone = buyer.getPhone();
		this.buyerAddress = buyer.getAddress();
		this.orderAmount = buyer.getCart().getProducts().stream()
				.map(item -> item.getProductPrice().multiply(new BigDecimal(item.getCount()))).reduce(BigDecimal::add)
				.orElse(new BigDecimal(0));
		this.orderStatus = "";

	}

	public Order(Long id, String buyerEmail, String buyerName, String buyerPhone, String buyerAddress,
			BigDecimal orderAmount, String orderStatus) {
		this.orderId = id;
		this.buyerEmail = buyerEmail;
		this.buyerName = buyerName;
		this.buyerPhone = buyerPhone;
		this.buyerAddress = buyerAddress;
		this.orderAmount = orderAmount;
		this.orderAmount = orderAmount;
		this.orderStatus = orderStatus;

	}
	public Order() {
	}
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Set<ProductOrdered> getProducts() {
		return products;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setProducts(Set<ProductOrdered> products) {
		this.products = products;
	}

}
