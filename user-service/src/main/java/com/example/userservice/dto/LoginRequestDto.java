package com.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    @NotBlank(message = "Email is required!")
    @Email(message = "Email Should be valid!")
    private String email;
    @NotBlank
    private String password;
}
