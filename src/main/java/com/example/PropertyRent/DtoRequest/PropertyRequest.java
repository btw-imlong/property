package com.example.PropertyRent.DtoRequest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyRequest {
    private String title;
    private String description;
    private String address;
    private BigDecimal price;
    private String electricityCost;
    private String waterCost;
}
