package com.example.orderservice.external;

import com.example.orderservice.dto.user.UserProductResponse;
import com.example.orderservice.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-MANAGEMENT", url = "http://localhost:8082/api/v1/users")
public interface UserClient {

    @GetMapping("/{userId}/products")
    ResponseEntity<UserProductResponse> userProduct(@PathVariable long userId);

    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> findUserById(@PathVariable long userId);

    @GetMapping("/credit-card/{userId}")
    ResponseEntity<String> findUserCreditCard(@PathVariable long userId);
}
