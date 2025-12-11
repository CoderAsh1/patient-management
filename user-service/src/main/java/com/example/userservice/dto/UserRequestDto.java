package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    String id;
    String name;
    String email;
    String role;
    String password;
}
