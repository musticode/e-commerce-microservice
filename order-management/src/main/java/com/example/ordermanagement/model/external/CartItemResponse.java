package com.example.ordermanagement.model.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {
    private long productId;
    private String name;
    private double price;
    private int quantity;
    private double subtotal;
}