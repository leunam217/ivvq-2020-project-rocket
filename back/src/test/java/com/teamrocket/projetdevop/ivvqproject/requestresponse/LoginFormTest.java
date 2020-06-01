package com.teamrocket.projetdevop.ivvqproject.requestresponse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginFormTest {

	private static Validator validator;

	@BeforeAll
	public static void setupContext() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void username_blank() {
		AuthentificationForm login = new AuthentificationForm("", "grominet");
		assertFalse(validator.validate(login).isEmpty());
	}

	@Test
	void password_blank() {
		AuthentificationForm login = new AuthentificationForm("toto", "");
		assertFalse(validator.validate(login).isEmpty());
	}

	@Test
	void get_username() {
		AuthentificationForm login = new AuthentificationForm("toto", "grominet");
		assert (login.getUsername().equals("toto"));
	}

	@Test
	void get_password() {
		AuthentificationForm login = new AuthentificationForm("toto", "grominet");
		assert (login.getPassword().equals("grominet"));
	}

}
