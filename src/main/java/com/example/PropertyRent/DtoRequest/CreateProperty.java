package com.example.PropertyRent.DtoRequest;

import lombok.Data;
import java.math.BigDecimal;

// CREATE
@Data
public class CreateProperty {
    private String title;
    private String description;
    private String address;
    private BigDecimal price;
    private String electricityCost;
    private String waterCost;
    private Long agentId; // optional if taken from JWT
}