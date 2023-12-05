package com.example.ordermanagement.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmOrderRequest {
    private long id;
}

/**
 * {
 *   "orderId": "789",
 *   "status": "success",
 *   "message": "Order created successfully."
 * }
 * */
