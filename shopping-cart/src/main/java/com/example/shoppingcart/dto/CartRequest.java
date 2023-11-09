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
    private String name;
    private long userId;
    private List<CartItemRequest> itemList;
    private int itemCount;
    private boolean isEmpty;
    private String note;
    private double totalPrice;
    private boolean requiresShipping;
    private double totalDiscount;
}
