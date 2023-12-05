package com.example.ordermanagement.dto.orderlineitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemResponse {
    private long productId;
    private int quantity;
    private double price;
    private double subtotal;
}

/**
 *  *   "items": [
 *  *     {
 *  *       "productId": "ABC123",
 *  *       "quantity": 2,
 *  *       "price": 25.00,
 *  *       "subtotal": 50.00
 *  *     },
 * */