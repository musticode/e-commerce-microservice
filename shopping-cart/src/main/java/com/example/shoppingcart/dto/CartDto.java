package com.example.shoppingcart.dto;

import com.example.shoppingcart.model.postgre.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private long id;
    private String name;
    private long userId;
    private List<CartItemDto> itemList;
    private int itemCount;
    private boolean isEmpty;
    private String note;
    private double totalPrice;
    private boolean requiresShipping;
    private double totalDiscount;
}
