package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import com.teamrocket.projetdevop.ivvqproject.service.impl.UserServiceImpl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	ShoppingCartRepository shoppingCartRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Test
	void should_save_user_successfully() {

		final User user = new User("bob@email.com", passwordEncoder.encode("secret"), "Bob", "21345", "Toulouse");

		userRepository.findByEmail(user.getEmail());

		given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
		given(userRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));

		User savedUser = userService.save(user);
		ShoppingCart cart = shoppingCartRepository.save(new ShoppingCart(savedUser));

		savedUser.setCart(cart);
		assertThat(savedUser).isNotNull();

		verify(userRepository, atLeast(1)).save(any(User.class));

	}

	@Test
	void should_throw_error_when_save_user_exist() {
		final User user = new User("bob@email.com", passwordEncoder.encode("secret"), "Bob", "21345", "Toulouse");

		given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

		assertThrows(IllegalArgumentException.class, () -> {

			userService.save(user);
		});

		verify(userRepository, never()).save(any(User.class));
	}

	@Test
	void update() {
		final User user = new User("bob@email.com", passwordEncoder.encode("password"), "Bob", "21345", "Toulouse");

		given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

		given(userRepository.save(user)).willReturn(user);

		final User expectedUser = userService.update(user);

		assertThat(expectedUser).isNotNull();

		verify(userRepository).save(any(User.class));
	}

	@Test

	void findOne() {
		final String email = "toto@email.com";
		final User user = new User("toto@email.com", passwordEncoder.encode("secret"), "Toto", "21345", "Mexique");

		given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

		final User expectedUser = userService.findOne(email);

		assertThat(expectedUser).isNotNull();

	}
}
