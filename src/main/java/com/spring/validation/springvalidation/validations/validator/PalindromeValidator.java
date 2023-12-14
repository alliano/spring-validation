package com.spring.validation.springvalidation.validations.validator;

import com.spring.validation.springvalidation.validations.PalindromeChecker;
import com.spring.validation.springvalidation.validations.annotations.Palindrome;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PalindromeValidator implements ConstraintValidator<Palindrome, String>{

    private final PalindromeChecker palindromeChecker;

    @Override
    public boolean isValid(String word, ConstraintValidatorContext context) {
        return this.palindromeChecker.isPalindrome(word);
    }

}
