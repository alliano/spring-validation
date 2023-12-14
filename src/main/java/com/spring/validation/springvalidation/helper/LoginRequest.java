package com.spring.validation.springvalidation.helper;

import com.spring.validation.springvalidation.validations.annotations.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class LoginRequest {
    
    @Email @NotBlank
    private String email;

    @Password @NotBlank
    private String password;
}
