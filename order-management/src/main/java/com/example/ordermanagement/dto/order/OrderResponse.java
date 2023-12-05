package com.example.ordermanagement.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private String status;
    private String message;
}
/**
 * {
 *   "orderId": "789",
 *   "status": "success",
 *   "message": "Order created successfully."
 * }
 * */
