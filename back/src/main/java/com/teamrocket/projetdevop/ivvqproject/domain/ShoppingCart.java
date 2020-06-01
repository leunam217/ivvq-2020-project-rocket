package com.teamrocket.projetdevop.ivvqproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class ShoppingCart implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JsonIgnore
	private User user;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<ProductOrdered> products = new HashSet<>();

	@Override
	public String toString() {
		return "Cart{" + "cartId=" + cartId + ", products=" + products + '}';
	}

	public ShoppingCart() {
	}
	public ShoppingCart(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ProductOrdered> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductOrdered> products) {
		this.products = products;
	}
}
