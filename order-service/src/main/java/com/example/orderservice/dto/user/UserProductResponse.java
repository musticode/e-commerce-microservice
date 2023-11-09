package com.example.orderservice.dto.user;

import com.example.orderservice.dto.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProductResponse {
    private long userId;
    private ProductResponse productResponse;
}