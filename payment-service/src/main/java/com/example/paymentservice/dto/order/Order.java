package com.example.paymentservice.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private String name;
    private long cartId;
    private Date cancelledAt;
    private String cancelReason;
    private double totalPrice;
    private long userId;
    private long sellerId;
    private long buyerId;
    private String shippingAddress;
}
