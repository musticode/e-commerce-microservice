package com.example.ordermanagement.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {
    private long userId;
    private List<ItemRequest> items;
}

/**
 * {
 *   "userId": "123",
 *   "items": [
 *     {
 *       "productId": "ABC123",
 *       "quantity": 2
 *     },
 *     // ... other items
 *   ]
 * }
 * */