package com.example.PropertyRent.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BecomeAgent {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String role;
}
