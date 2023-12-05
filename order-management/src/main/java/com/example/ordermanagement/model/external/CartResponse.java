package com.example.ordermanagement.model.external;

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