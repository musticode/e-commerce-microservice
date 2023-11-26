package com.example.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private long cartId;
    private int totalItems;
    private double totalPrice;
    private List<CartItemResponse> item;
}


/**
 * {
 *   "cartId": "456",
 *   "totalItems": 3,
 *   "totalPrice": 50.00,
 *   "item": {
 *     "productId": "ABC123",
 *     "name": "Product Name",
 *     "price": 25.00,
 *     "quantity": 2,
 *     "subtotal": 50.00
 *   }
 * }
 * */
