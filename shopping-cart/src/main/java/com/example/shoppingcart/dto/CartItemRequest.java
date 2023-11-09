package com.example.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRequest {
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
