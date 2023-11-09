package com.example.productcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private long quantity;
    private double price;
    private String description;
    private String sellerId;
    private long categoryId;
    private String manufacturer;
}
