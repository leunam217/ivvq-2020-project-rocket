package com.teamrocket.projetdevop.ivvqproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamrocket.projetdevop.ivvqproject.domain.*;
import com.teamrocket.projetdevop.ivvqproject.service.ProductOrderedService;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.ShoppingCartService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import com.teamrocket.projetdevop.ivvqproject.util.LuhnAlgorithm;
import com.teamrocket.projetdevop.ivvqproject.utils.AbstractRestControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.not;

@AutoConfigureMockMvc(addFilters = false)
public class CartControllerTest extends AbstractRestControllerTest {

	@MockBean
	ShoppingCartService shoppingCartService;

	@MockBean
	UserService userService;

	@MockBean
	ProductService productService;

	@Mock
	PasswordEncoder passwordEncoder;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	ProductOrderedService productOrderedService;

	LuhnAlgorithm luhnAlgorithm;

	@Mock
	Principal principal;

	private List<ShoppingCart> carts;

	Collection<ProductOrdered> productOrdereds = new HashSet<>();

	private User user;
	private ShoppingCart shoppingCart;

	@BeforeEach
	void setup() {
		this.user = new User("bobemail.com", passwordEncoder.encode("secret"), "Bob", "21345", "Toulouse");
		this.shoppingCart = new ShoppingCart(user);
		this.carts = new ArrayList<>();
		this.carts.add(new ShoppingCart(user));

		this.productOrdereds.add(new ProductOrdered());

		this.luhnAlgorithm = new LuhnAlgorithm();

	}

	@Test
	void getCart() throws Exception {
		given(userService.findOne(user.getName())).willReturn(user);
		getMockMvc().perform(get("/cart").principal(principal)).andExpect(status().isOk());
	}

	@Test
	void edit() throws Exception {

		String itemId = "B01";
		given(userService.findOne(user.getName())).willReturn(user);

		productOrderedService.update("B01", 3, user);

		getMockMvc().perform(put("/cart/{itemId}", 3, itemId).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(3)).principal(principal)).andExpect(status().isOk());
	}

	@Test
	void addItemToCart() throws Exception {

		final Product product = new Product("B01", "Rocket", new BigDecimal(123), 100, "desc", "icon");

		ItemForm itemForm = new ItemForm(2, "B01");

		given(productService.findOne(itemForm.getProductId())).willReturn(product);

		given(userService.findOne(user.getName())).willReturn(user);

		getMockMvc()
				.perform(post("/cart/add").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(itemForm)).principal(principal))
				.andExpect(status().isOk());
	}

	@Test
	void modifyItem() throws Exception {

		ItemForm itemForm = new ItemForm(3, "B01");
		given(userService.findOne(user.getName())).willReturn(user);

		getMockMvc()
				.perform(put("/cart/{itemId}", itemForm.getProductId(), itemForm.getQuantity(), principal)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(itemForm.getQuantity())).principal(principal))
				.andExpect(status().isOk());

	}

	@Test
	void deleteItem() throws Exception {

		ItemForm itemForm = new ItemForm(3, "B01");
		given(userService.findOne(user.getName())).willReturn(user);

		doNothing().when(shoppingCartService).delete(itemForm.getProductId(), user);

		this.getMockMvc().perform(delete("/cart/{itemId}", itemForm.getProductId(), principal).principal(principal))
				.andExpect(status().isOk());
	}

	@Test
	void checkoutCartNotValideCart() throws Exception {

		String cartNum = "546779514710751";
		luhnAlgorithm.setCartNum(cartNum);
		given(userService.findOne(user.getName())).willReturn(user);

		luhnAlgorithm.validateCreditCart(cartNum);
		this.getMockMvc()
				.perform(post("/cart/checkout", luhnAlgorithm, principal).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(luhnAlgorithm)).principal(principal))
				.andExpect(status().isOk());

	}
	@Test
	void checkoutValideCart() throws Exception {

		String cartNum = "5467795147107510";
		luhnAlgorithm.setCartNum(cartNum);
		given(userService.findOne(user.getName())).willReturn(user);

		luhnAlgorithm.validateCreditCart(cartNum);
		shoppingCartService.placeOrder(user);
		this.getMockMvc()
				.perform(post("/cart/checkout", luhnAlgorithm, principal).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(luhnAlgorithm)).principal(principal))
				.andExpect(status().isOk());

	}

}
