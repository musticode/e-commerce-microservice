package com.example.shoppingcart.external;

import com.example.shoppingcart.external.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-MANAGEMENT", url = "${application.config.user-management}")
public interface UserClient {
    @GetMapping("/details/{userId}")
    ResponseEntity<User> findUserDetailsById(@PathVariable long userId);
}
