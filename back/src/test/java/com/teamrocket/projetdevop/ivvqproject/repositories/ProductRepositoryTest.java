package com.teamrocket.projetdevop.ivvqproject.repositories;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

	@Mock
	ProductRepository repository;

	private Product product;

	@BeforeEach()
	void setup() {
		this.product = new Product("B001", "Rocket", new BigDecimal(123), 100, "desc", "icon");

	}

	@Test
	public void findByIdIfPresent() {

		Optional<Product> productOptional = repository.findById(product.getProductId());
		assertThat(productOptional.isPresent()).isNotNull();
	}

	@Test
	public void findAllByProductNameContaining() {
		List<Product> productOptional = repository.findAllByProductNameContaining(product.getProductName());
		assertTrue(productOptional.isEmpty());
	}

}
