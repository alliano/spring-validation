package com.spring.validation.springvalidation.validations;

import org.springframework.stereotype.Component;

@Component
public class PalindromeChecker {
    
    public Boolean isPalindrome(String word) {
        String reverse = new StringBuilder(word).reverse().toString();
        return reverse.equals(word);
    }
}
