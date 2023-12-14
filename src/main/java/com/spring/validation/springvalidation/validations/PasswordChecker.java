package com.spring.validation.springvalidation.validations;

import org.springframework.stereotype.Component;

@Component
public class PasswordChecker {
    
    public boolean isPasswordValid(String password) {
        if (password.length() < 5) return false;
        return true;
    }
}
