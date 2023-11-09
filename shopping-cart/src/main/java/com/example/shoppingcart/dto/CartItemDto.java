package com.example.shoppingcart.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
    private long id;
    private String name;
    private double price;
    private double finalPrice;
    private String message;
    private long productId;
    private int quantity;
    private String sku;
    private String title;
    private String vendor;
}
