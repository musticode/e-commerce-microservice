package com.example.paymentservice.external;

import com.example.paymentservice.dto.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER-MANAGEMENT", url = "${application.config.user-management}")
public interface UserClient {
    @GetMapping("/details/{userId}")
    ResponseEntity<User> findUserDetailsById(@PathVariable long userId);

    @GetMapping("/credit-card/{userId}")
    ResponseEntity<String> findUserCreditCard(@PathVariable long userId);

}
