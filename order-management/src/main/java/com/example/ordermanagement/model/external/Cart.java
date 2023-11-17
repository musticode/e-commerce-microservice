package com.example.ordermanagement.model.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;
    private String name;
    private long userId;
    private List<CartItem> itemList;
    private int itemCount;
    private boolean isEmpty;
    private String note;
    private double totalPrice;
    private boolean requiresShipping;
    private double totalDiscount;
}
