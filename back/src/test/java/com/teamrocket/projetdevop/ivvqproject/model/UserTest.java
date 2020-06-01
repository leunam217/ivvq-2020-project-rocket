package com.teamrocket.projetdevop.ivvqproject.model;

import com.teamrocket.projetdevop.ivvqproject.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

	private static Validator validator;

	@Mock
	PasswordEncoder passwordEncoder;

	@BeforeAll
	public static void setupContext() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void user_email_without_arobase() {
		final User user = new User("bobemail.com", passwordEncoder.encode("secret"), "Bob", "21345", "Toulouse");

		assertFalse(validator.validate(user).isEmpty());

	}

	@Test
	void user_email_empty() {
		final User user = new User("", passwordEncoder.encode("secret"), "Bob", "21345", "Toulouse");

		assertFalse(validator.validate(user).isEmpty());

	}

	@Test
	void user_password_empty() {
		final User user = new User("bob@email.com", passwordEncoder.encode(""), "Bob", "21345", "Toulouse");

		assertFalse(validator.validate(user).isEmpty());

	}

	@Test
	void user_password_less_than_3() {
		final User user = new User("bob@email.com", passwordEncoder.encode("b"), "Bob", "21345", "Toulouse");
		assertFalse(validator.validate(user).isEmpty());
	}

	@Test
	void user_name_empty() {
		final User user = new User("bob@email.com", passwordEncoder.encode("bdrf"), "", "21345", "Toulouse");

		assertFalse(validator.validate(user).isEmpty());

	}

	@Test
	void user_phone_empty() {
		final User user = new User("bob@email.com", passwordEncoder.encode("bdrf"), "bob", "", "Toulouse");

		assertFalse(validator.validate(user).isEmpty());

	}

	@Test
	void user_address_empty() {
		final User user = new User("bob@email.com", passwordEncoder.encode("bdrf"), "bob", "12321", "");

		assertFalse(validator.validate(user).isEmpty());
	}

	@Test
	void get_user_email() {
		User user = new User("bob@email.com", passwordEncoder.encode("bdrf"), "bob", "12321", "Toulouse");
		assert (user.getEmail().equals("bob@email.com"));

	}

	@Test
	void get_user_name() {
		User user = new User("bob@email.com", passwordEncoder.encode("bdrf"), "bob", "12321", "Toulouse");
		assert (user.getName().equals("bob"));

	}

	@Test
	void get_user_phone() {
		User user = new User("bob@email.com", passwordEncoder.encode("bdrf"), "bob", "12321", "Toulouse");
		assert (user.getPhone().equals("12321"));
	}

	@Test
	void get_user_address() {
		User user = new User("bob@email.com", passwordEncoder.encode("bdrf"), "bob", "12321", "Toulouse");
		assert (user.getAddress().equals("Toulouse"));
	}

	@Test
	void get_user_role_customer() {
		User user = new User("bob@email.com", passwordEncoder.encode("bdrf"), "bob", "12321", "Toulouse");
		assert (user.getRole().equals("ROLE_CUSTOMER"));
	}

}
