package com.example.paymentservice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Long id;
    private String name;
    private long quantity;
    private double price;
    private String description;
    private String available;
    private String sellerId;
    private String manufacturer;
}
