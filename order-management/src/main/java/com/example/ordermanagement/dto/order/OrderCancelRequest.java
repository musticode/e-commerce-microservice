package com.example.ordermanagement.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCancelRequest {
    private long id;
    private String status;
    private String reason;
}
