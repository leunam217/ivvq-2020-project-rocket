package com.teamrocket.projetdevop.ivvqproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.util.DataLoader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerIT {

	@Autowired
	private DataLoader dataLoader;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testFindAllProduct() throws Exception {

		// given: une liste de produit "testProduct" en base
		List<Product> testedProduct = dataLoader.getProducts();

		// when: l'utilisateur émet une requête pour obtenir la liste des produits
		mockMvc.perform(get("/product").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(testedProduct))).andExpect(status().isOk());

		// then: le résultat obtenu contient tous les produits persistée
		assertThat(testedProduct).isNotNull();

	}

	@Test
	public void testFindOneProduct() throws Exception {

		// given: un produit "prod" en base
		Product prod = dataLoader.getRocket1();
		String productId = prod.getProductId();
		// when: l'utilisateur émet une requête pour obtenir la liste des produits
		mockMvc.perform(get("/product/{productId}", productId).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(prod))).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId", is(prod.getProductId())))
				.andExpect(jsonPath("$.productName", is(prod.getProductName())))
				.andExpect(jsonPath("$.productStock", is(prod.getProductStock())))
				.andExpect(jsonPath("$.productDescription", is(prod.getProductDescription())))
				.andExpect(jsonPath("$.productIcon", is(prod.getProductIcon())));

		// then: le résultat obtenu contient tous les produits persistée
		assertThat(prod).isNotNull();

	}

	@Test
	void editRocket2() throws Exception {

		Product rocket2 = dataLoader.getRocket2();
		rocket2.setProductStock(25);
		dataLoader.initRocket2();
		mockMvc.perform(put("/seller/product/{id}/edit", rocket2.getProductId())
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(rocket2)))
				.andExpect(status().isOk());

		assertEquals(25, rocket2.getProductStock());
	}

}
