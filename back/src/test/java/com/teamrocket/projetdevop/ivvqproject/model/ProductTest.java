package com.teamrocket.projetdevop.ivvqproject.model;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductTest {

	private static Validator validator;

	@BeforeAll
	public static void setupContext() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void product_id_empty() {
		final Product product = new Product("", "", new BigDecimal(123), 100, "desc", "icon");
		assertFalse(validator.validate(product).isEmpty());
	}

	@Test
	void product_name_empty() {
		final Product product = new Product("B001", "", new BigDecimal(123), 100, "desc", "icon");
		assertFalse(validator.validate(product).isEmpty());
	}

	@Test
	void product_description_empty() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(123), 100, "", "icon");
		assertFalse(validator.validate(product).isEmpty());
	}

	@Test
	void product_price_negatif() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(-1), 100, "desc", "icon");
		assertFalse(validator.validate(product).isEmpty());
	}

	@Test
	void get_product_id() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(12), 2, "desc", "icon");
		assert (product.getProductId().equals("B001"));
	}

	@Test
	void get_product_name() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(12), 2, "desc", "icon");
		assert (product.getProductName().equals("Rocket"));
	}

	@Test
	void get_product_price() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(12), 2, "desc", "icon");

		assert (product.getProductPrice().equals(new BigDecimal(12)));
	}

	@Test
	void get_product_stock() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(12), 2, "desc", "icon");
		assert (product.getProductStock().equals(2));
	}

	@Test
	void get_product_description() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(12), 2, "desc", "icon");
		assert (product.getProductDescription().equals("desc"));
	}

	@Test
	void get_product_icon() {
		String icon = "AmazingIcon";
		final Product product = new Product("B001", "Rocket", new BigDecimal(12), 2, "desc", "icon");
		product.setProductIcon(icon);
		assert (product.getProductIcon().equals("AmazingIcon"));
	}

}
