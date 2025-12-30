package com.example.PropertyRent.DtoRequest;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;  // must match JSON key

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
