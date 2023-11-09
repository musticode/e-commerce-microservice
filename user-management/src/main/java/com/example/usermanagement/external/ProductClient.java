package com.example.usermanagement.external;

import com.example.usermanagement.dto.product.ProductResponse;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PRODUCT-CATALOG", url = "http://localhost:8081/api/v1/products") //, url = "${application.config.product-catalog}"
public interface ProductClient {

    @GetMapping("/{productId}")
    ResponseEntity<ProductResponse> findProductWithId(@PathVariable long productId);


    // http://localhost:8081/api/v1/products
}
