package com.example.paymentservice.external;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "USER-MANAGEMENT")
public interface UserClient {
}
