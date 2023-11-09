package com.example.ordermanagement.external;

import com.example.ordermanagement.dto.ProductResponse;
import com.example.ordermanagement.model.external.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-CATALOG", url = "${application.config.product-url}")
public interface ProductClient {

    @GetMapping("/{productId}")
    ResponseEntity<Product> findProductById(@PathVariable long productId);

    @GetMapping
    ResponseEntity<List<ProductResponse>> findAllProducts();
}
