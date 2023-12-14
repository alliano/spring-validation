package com.spring.validation.springvalidation;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Person {
    
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
