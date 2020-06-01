package com.teamrocket.projetdevop.ivvqproject.requestresponse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class JwtResponseTest {

	private static Validator validator;

	@BeforeAll
	public static void setupContext() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void get_token() {
		JwtResponse jwtResponse = new JwtResponse("214HEKBDnbfgs", "myaccount", "toto", "ROLE_CUSTOMER");
		assert (jwtResponse.getToken().equals("214HEKBDnbfgs"));

	}

	@Test
	void get_account() {
		JwtResponse jwtResponse = new JwtResponse("214HEKBDnbfgs", "myaccount", "toto", "ROLE_CUSTOMER");
		assert (jwtResponse.getAccount().equals("myaccount"));

	}
	@Test
	void get_name() {
		JwtResponse jwtResponse = new JwtResponse("214HEKBDnbfgs", "myaccount", "toto", "ROLE_CUSTOMER");
		assert (jwtResponse.getName().equals("toto"));

	}
	@Test
	void get_role() {
		JwtResponse jwtResponse = new JwtResponse("214HEKBDnbfgs", "myaccount", "toto", "ROLE_CUSTOMER");
		assert (jwtResponse.getRole().equals("ROLE_CUSTOMER"));

	}
}
