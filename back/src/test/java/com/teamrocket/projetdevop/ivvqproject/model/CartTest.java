package com.teamrocket.projetdevop.ivvqproject.model;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class CartTest {

	private static Validator validator;

	@Mock
	PasswordEncoder passwordEncoder;

	@BeforeAll
	public static void setupContext() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void get_user() {
		final User user = new User("bob@email.com", passwordEncoder.encode(""), "Bob", "21345", "Toulouse");
		final ShoppingCart cart = new ShoppingCart(user);
		assert (cart.getUser().equals(user));
	}

	@Test
	void set_user() {
		final User user = new User("bob@email.com", passwordEncoder.encode(""), "Bob", "21345", "Toulouse");
		final ShoppingCart cart = new ShoppingCart(user);

		User user1 = new User("bob1@email.com", passwordEncoder.encode(""), "Bob", "21345", "Toulouse");
		cart.setUser(new User("bob1@email.com", passwordEncoder.encode(""), "Bob", "21345", "Toulouse"));

		assert (cart.getUser().equals(user1));
	}

	@Test
	void get_products() {
		Set<ProductOrdered> productList = new HashSet<>();
		final ShoppingCart cart = new ShoppingCart();
		cart.setProducts(productList);
		assert (cart.getProducts().equals(productList));
	}

}
