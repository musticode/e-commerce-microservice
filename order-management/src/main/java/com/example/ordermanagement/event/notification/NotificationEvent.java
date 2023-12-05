package com.example.ordermanagement.event.notification;

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
