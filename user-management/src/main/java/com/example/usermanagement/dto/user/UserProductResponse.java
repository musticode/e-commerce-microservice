package com.example.usermanagement.dto.user;


import com.example.usermanagement.dto.product.ProductResponse;
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
