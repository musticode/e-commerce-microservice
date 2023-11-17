package com.example.productcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private long quantity;
    private double price;
    private String description;
    private String available;
    private String sellerId;
    private String manufacturer;
}
