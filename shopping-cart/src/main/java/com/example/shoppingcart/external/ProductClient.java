package com.example.shoppingcart.external;

import com.example.shoppingcart.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-CATALOG", url = "${application.config.product-catalog}")
public interface ProductClient {

    @GetMapping("/{productId}")
    ResponseEntity<Product> findProductById(@PathVariable long productId);

}
