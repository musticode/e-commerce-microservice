package com.example.ordermanagement.dto.order;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.OrderLineItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String name;
    private long cartId;
    private double totalPrice;
    private long userId;
    private List<OrderLineItem> orderLineItems;
    private long buyerId;
    private long sellerId;
}
