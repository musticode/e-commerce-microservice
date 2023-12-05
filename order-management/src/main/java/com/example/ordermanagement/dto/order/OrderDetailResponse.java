package com.example.ordermanagement.dto.order;

import com.example.ordermanagement.dto.orderlineitem.OrderLineItemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private long id;
    private long userId;
    private List<OrderLineItemResponse> items;
    private double totalPrice;
    private String status;
}

/**
 * {
 *   "orderId": "789",
 *   "userId": "123",
 *   "items": [
 *     {
 *       "productId": "ABC123",
 *       "quantity": 2,
 *       "price": 25.00,
 *       "subtotal": 50.00
 *     },
 *     // ... other items
 *   ],
 *   "totalPrice": 50.00,
 *   "status": "confirmed"
 * }
 * */
