package com.spring.validation.springvalidation;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest
class SpringValidationApplicationTests {

	@Autowired
	private Validator validation;

	@Test
	public void testValidatePersonNotValid() {
		Set<ConstraintViolation<Person>> validate = this.validation.validate(Person.builder().firstName("").build());
		Assertions.assertFalse(validate.isEmpty());
		Assertions.assertEquals(2, validate.size());
	}

	@Test
	public void testValidatePersonSucces() {
		Set<ConstraintViolation<Person>> validate = this.validation.validate(new Person("Abdilah", "alli"));
		Assertions.assertTrue(validate.isEmpty());
		Assertions.assertEquals(0, validate.size());
	}
}
