package com.example.paymentservice.external;

import com.example.paymentservice.model.external.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PRODUCT-CATALOG" , url = "http://localhost:8081/api/v1/products")
public interface ProductClient {

    @GetMapping("/{productId}")
    ResponseEntity<Product> findProductById(@PathVariable long productId);

    @GetMapping("/quantity/{productId}")
    ResponseEntity<Long> findProductQuantityById(@PathVariable long productId);

}

