package com.example.paymentservice.external;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "ORDER-MANAGEMENT")
public interface OrderClient {
}
