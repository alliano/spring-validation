package com.spring.validation.springvalidation;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.validation.springvalidation.helper.LoginRequest;
import com.spring.validation.springvalidation.helper.Pow;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest
public class CustomValidationTest {
    
    @Autowired
    private Validator validator;

    @Test
    public void testPalindromeTrue() {
        Set<ConstraintViolation<Pow>> validate = this.validator.validate(new Pow("kodok"));
        Assertions.assertEquals(0, validate.size());
        Assertions.assertTrue(validate.isEmpty());
    }

    @Test
    public void testPalindromeFalse() {
        Set<ConstraintViolation<Pow>> validate = this.validator.validate(new Pow("babi"));
        Assertions.assertFalse(validate.isEmpty());
        Assertions.assertEquals(1, validate.size());
    }

    @Test
    public void testPasswordInValid() {
        LoginRequest loginRequest = LoginRequest.builder()
                    .email("alliano@gmail.com")
                    .password("ab")
                    .build();
        Set<ConstraintViolation<LoginRequest>> validate = this.validator.validate(loginRequest);
        Assertions.assertEquals("password harus lebih dari 5 karakter", validate.stream().findFirst().get().getMessage());
        Assertions.assertEquals(1, validate.size());
        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
     public void testPasswordValid() {
        LoginRequest loginRequest = LoginRequest.builder()
                    .email("alliano@gmail.com")
                    .password("cjery39797dd")
                    .build();
        Set<ConstraintViolation<LoginRequest>> validate = this.validator.validate(loginRequest);
        Assertions.assertTrue(validate.isEmpty());
        Assertions.assertEquals(0, validate.size());
    }
}
