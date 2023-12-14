package com.spring.validation.springvalidation.helper;

import com.spring.validation.springvalidation.validations.annotations.Palindrome;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Pow {
    
    @Palindrome
    private String word;
}
