package com.example.ordermanagement.external;

import com.example.ordermanagement.model.external.Cart;
import com.example.ordermanagement.model.external.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SHOPPING-CART", url = "${application.config.cart-url}")
public interface CartClient {

    @GetMapping("/{cartId}")
    ResponseEntity<Cart> findCartById(@PathVariable long cartId);

    @GetMapping("/user/{userId}")
    ResponseEntity<CartResponse> getUserCart(@PathVariable long userId);
}
