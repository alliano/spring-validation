package com.spring.validation.springvalidation.helper;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Validated
public interface Greeting {
    
    public String greeting(@NotBlank String name);
}
