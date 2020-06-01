package com.teamrocket.projetdevop.ivvqproject.model;

import com.teamrocket.projetdevop.ivvqproject.domain.ItemForm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ItemTest {

	private static Validator validator;

	@BeforeAll
	public static void setupContext() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void quantity_not_null() {
		ItemForm itemForm = new ItemForm(0, "B001");
		assertFalse(validator.validate(itemForm).isEmpty());
	}

	@Test
	void product_id_empty() {
		ItemForm itemForm = new ItemForm(2, "");
		assertFalse(validator.validate(itemForm).isEmpty());
	}

	@Test
	void get_quantity() {
		ItemForm itemForm = new ItemForm(2, "B001");
		assert (itemForm.getQuantity().equals(2));

	}

	@Test
	void get_product_id() {
		ItemForm itemForm = new ItemForm(2, "B001");
		assert (itemForm.getProductId().equals("B001"));

	}

}
