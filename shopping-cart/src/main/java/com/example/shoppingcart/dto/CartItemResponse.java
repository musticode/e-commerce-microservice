package com.example.shoppingcart.dto;

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
/**
 *  *   "item": {
 *  *     "productId": "ABC123",
 *  *     "name": "Product Name",
 *  *     "price": 25.00,
 *  *     "quantity": 2,
 *  *     "subtotal": 50.00
 *  *   }
 * */
