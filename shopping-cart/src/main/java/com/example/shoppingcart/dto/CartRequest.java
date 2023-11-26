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
public class CartRequest {
    private long userId;
    private long productId;
    private int quantity;
}

/**
 * {
 *   "userId": "123",
 *   "productId": "ABC123",
 *   "quantity": 2
 * }
 *
 * */
