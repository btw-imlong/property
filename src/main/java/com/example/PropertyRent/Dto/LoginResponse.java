package com.example.PropertyRent.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String fullName;
    private String email;
    private String token;
    private String role;
}
