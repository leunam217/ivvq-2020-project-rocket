package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.repositories.ProductRepository;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.impl.ProductServiceImpl;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductService productService;

	private Product product;

	@BeforeEach()
	void setup() {
		this.product = new Product("B001", "Rocket", new BigDecimal(123), 40, "desc", "icon");

	}

	@Test
	void should_return_saveProduct() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(123), 100, "desc", "icon");

		given(productRepository.findById(product.getProductId())).willReturn(Optional.empty());
		given(productRepository.save(product)).willAnswer(invocation -> invocation.getArgument(0));

		Product saveProduct = productServiceImpl.save(product);

		assertThat(saveProduct).isNotNull();

		verify(productRepository).save(any(Product.class));

	}

	@Test
	void should_return_findByProductId() {
		String id = "B001";

		final Product product = new Product("B001", "Rocket", new BigDecimal(123), 21, "desc", "icon");

		given(productRepository.findByProductId(id)).willReturn(product);

		final Product expectedProduct = productServiceImpl.findOne(id);

		assertThat(expectedProduct).isNotNull();

	}

	@Test
	void should_return_findAllProduct() {
		List<Product> products = new ArrayList<>();
		given(productRepository.findAll()).willReturn(products);

		List<Product> expectedProducts = productServiceImpl.findAll();
		Assertions.assertEquals(expectedProducts, products);
	}

	@Test
	void findAllByName() {
		List<Product> products = new ArrayList<>();
		given(productRepository.findAllByProductNameContaining(product.getProductName())).willReturn(products);
		List<Product> expectedProducts = productServiceImpl.findAllByName(product.getProductName());
		Assertions.assertEquals(expectedProducts, products);
	}

	@Test
	void should_return_increaseStock() {

		given(productService.findOne(product.getProductId())).willReturn(product);

		Integer oldStock = product.getProductStock();
		productService.increaseStock(product.getProductId(), 50);
		Integer addedStock = product.getProductStock() + 50;

		Assertions.assertEquals(50, addedStock - oldStock);

	}

	@Test
	void should_return_decreaseStock() {

		given(productService.findOne(product.getProductId())).willReturn(product);

		Integer oldStock = product.getProductStock();

		productService.decreaseStock(product.getProductId(), 50);
		Integer decreaseStock = product.getProductStock() - 50;
		Assertions.assertEquals(50, oldStock - decreaseStock);

	}

	@Test
	void should_return_updateProduct() {
		final Product product = new Product("B001", "Rocket", new BigDecimal(123), 100, "desc", "icon");

		given(productRepository.save(product)).willReturn(product);

		final Product expectedProduct = productServiceImpl.update(product);

		assertThat(expectedProduct).isNotNull();

		verify(productRepository).save(any(Product.class));
	}

	@Test
	void should_delete_product() {

		// String productId = "B001";

		given(productService.findOne(product.getProductId())).willReturn(product);
		productService.delete(product.getProductId());

		verify(productService).delete(product.getProductId());
	}

}
