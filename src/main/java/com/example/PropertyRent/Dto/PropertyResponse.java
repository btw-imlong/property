package com.example.PropertyRent.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PropertyResponse {
    private Long id;
    private String title;
    private String address;
    private BigDecimal price;
    private Long agentId;
}
