package com.example.ordermanagement.model.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long id;

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
