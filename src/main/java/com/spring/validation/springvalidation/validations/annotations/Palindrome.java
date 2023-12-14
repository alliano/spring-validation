package com.spring.validation.springvalidation.validations.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.spring.validation.springvalidation.validations.validator.PalindromeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = PalindromeValidator.class)
public @interface Palindrome {
    
    public String message() default "{palindrome.message}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
