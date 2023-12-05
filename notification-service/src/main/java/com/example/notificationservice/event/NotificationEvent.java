package com.example.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {
    private long userId;
    private String message;
    private NotificationType notificationType;
    private long orderId;
}


/**
 *
 * {
 *   "userId": "123",
 *   "message": "Your order has been placed successfully!",
 *   "notificationType": "OrderConfirmation",
 *   "additionalData": {
 *     "orderId": "789",
 *     "totalAmount": 50.00
 *   }
 * }
 *
 * */