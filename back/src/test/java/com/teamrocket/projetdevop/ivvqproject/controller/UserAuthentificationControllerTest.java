package com.teamrocket.projetdevop.ivvqproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.requestresponse.AuthentificationForm;
import com.teamrocket.projetdevop.ivvqproject.security.JWT.JsonWebTokenProvider;
import com.teamrocket.projetdevop.ivvqproject.service.ShoppingCartService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import com.teamrocket.projetdevop.ivvqproject.service.impl.UserServiceImpl;
import com.teamrocket.projetdevop.ivvqproject.utils.AbstractRestControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.Principal;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import static junit.framework.TestCase.assertEquals;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserAuthentificationControllerTest extends AbstractRestControllerTest {

	@MockBean
	private UserService userService;

	@MockBean
	ShoppingCartService shoppingCartService;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		SecurityContextHolder.clearContext();

	}

	@Test
	public void unsuccessful_authentication_with_wrong_password() throws Exception {
		getMockMvc()
				.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\": \"user\", \"password\": \"wrong\"}"))
				.andExpect(status().isUnauthorized()).andExpect(content().string(not(containsString("token"))));
	}

	@Test
	public void unsuccessful_authentication_with_not_existing_user() throws Exception {
		getMockMvc()
				.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\": \"user\", \"password\": \"not_existing\"}"))
				.andExpect(status().isUnauthorized()).andExpect(content().string(not(containsString("token"))));
	}

	@Test
	public void unsuccessful_authentication_with_disabled() throws Exception {
		getMockMvc()
				.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\": \"disabled\", \"password\": \"password\"}"))
				.andExpect(status().isUnauthorized()).andExpect(content().string(not(containsString("token"))));
	}

	@Test
	@WithMockUser(username = "bobemail.com", password = "secret")
	void saveUser() throws Exception {
		given(userService.save(any(User.class))).willAnswer((invocation) -> invocation.getArgument(0));

		final User user = new User("bobemail.com", passwordEncoder.encode("secret"), "Bob", "21345", "Toulouse");
		getMockMvc()
				.perform(post("/register").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
