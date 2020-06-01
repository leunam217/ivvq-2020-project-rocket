package com.teamrocket.projetdevop.ivvqproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
public class ProductOrdered implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private ShoppingCart cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Order order;

	@NotEmpty
	private String productId;

	@NotEmpty
	private String productName;

	@NotEmpty
	private String productDescription;

	private String productIcon;

	@Min(0)
	private BigDecimal productPrice;

	@Min(0)
	private Integer productStock;

	@Min(1)
	private Integer count;

	public ProductOrdered(Product product, Integer quantity) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.productIcon = product.getProductIcon();

		this.productPrice = product.getProductPrice();
		this.productStock = product.getProductStock();
		this.count = quantity;
	}

	@Override
	public String toString() {
		return "ProductInOrder{" + "id=" + id + ", productId='" + productId + '\'' + ", productName='" + productName
				+ '\'' + ", productDescription='" + productDescription + '\'' + ", productIcon='" + productIcon + '\''
				+ ", productPrice=" + productPrice + ", productStock=" + productStock + ", count=" + count + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		ProductOrdered that = (ProductOrdered) o;
		return Objects.equals(id, that.id) && Objects.equals(productId, that.productId)
				&& Objects.equals(productName, that.productName)
				&& Objects.equals(productDescription, that.productDescription)
				&& Objects.equals(productIcon, that.productIcon) && Objects.equals(productPrice, that.productPrice);
	}

	public ProductOrdered() {
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), id, productId, productName, productDescription, productIcon,
				productPrice);
	}

	public Long getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getProductId() {
		return productId;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
