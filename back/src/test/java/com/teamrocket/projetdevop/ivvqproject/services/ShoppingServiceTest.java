package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShoppingServiceTest {

	@Mock
	PasswordEncoder passwordEncoder;

	ShoppingCart shoppingCart;
	User user;

	Collection<ProductOrdered> productOrdereds = new HashSet<>();

	@BeforeEach
	void setup() {
		this.user = new User("bob@email.com", passwordEncoder.encode("secret"), "Bob", "21345", "Toulouse");
		this.shoppingCart = new ShoppingCart(user);

		productOrdereds
				.add(new ProductOrdered(new Product("B01", "coucou", new BigDecimal(123), 100, "desc", "icon"), 2));
	}

	@Test
	void should_return_getCart() {
		ShoppingCart cart = new ShoppingCart(user);
		assertEquals(cart, shoppingCart);
	}

}
