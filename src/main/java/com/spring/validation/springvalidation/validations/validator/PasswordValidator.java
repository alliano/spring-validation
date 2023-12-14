package com.spring.validation.springvalidation.validations.validator;

import com.spring.validation.springvalidation.validations.PasswordChecker;
import com.spring.validation.springvalidation.validations.annotations.Password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PasswordValidator implements ConstraintValidator<Password, String>{

    private final PasswordChecker passwordChecker;

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        return this.passwordChecker.isPasswordValid(arg0);
    }
}
