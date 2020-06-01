package com.teamrocket.projetdevop.ivvqproject.utils;

import com.teamrocket.projetdevop.ivvqproject.util.LuhnAlgorithm;
import org.hibernate.validator.internal.constraintvalidators.hv.LuhnCheckValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class LunhAlgorithmTest {

	private static Validator validator;

	@BeforeAll
	public static void setupContext() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testBadLength() {
		LuhnAlgorithm valide = new LuhnAlgorithm();
		String number = "123456789123"; // 12 length
		valide.validateCreditCart(number);
		assertTrue(validator.validate(valide).isEmpty());

	}

	@Test
	public void testGoodLength() {
		LuhnAlgorithm valide = new LuhnAlgorithm();
		String number = "12345678912345678912"; // 12 length
		valide.validateCreditCart(number);
		assertTrue(validator.validate(valide).isEmpty());

	}

	@Test
	public void testValidNumbers() {
		LuhnAlgorithm valide = new LuhnAlgorithm();
		String number = "378282246310005"; // test 1 (AmEx card)

		valide.validateCreditCart(number);

		assertTrue(validator.validate(valide).isEmpty());

	}

	@Test
	void get_credit_cart() {
		String creditCart = "378282246310005";
		LuhnAlgorithm luhnAlgorithm = new LuhnAlgorithm();
		luhnAlgorithm.setCartNum(creditCart);
		assert (luhnAlgorithm.getCartNum().equals(creditCart));
	}

}
