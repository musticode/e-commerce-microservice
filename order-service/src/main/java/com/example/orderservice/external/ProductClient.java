package com.example.orderservice.external;

import com.example.orderservice.constant.ProductClientConstant;
import com.example.orderservice.dto.product.ProductQuantityRequest;
import com.example.orderservice.dto.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "PRODUCT-SERVICE", url = "http://localhost:8080/api/v1/products")
public interface ProductClient {
    @GetMapping(ProductClientConstant.PRODUCT_QUANTITY_GET_BY_ID)
    ResponseEntity<Integer> getProductQuantityByProductId(@PathVariable long productId);

    @GetMapping(ProductClientConstant.PRODUCT_GET_BY_ID)
    ResponseEntity<ProductResponse> getProductById(@PathVariable long productId);


    @PostMapping("/product-quantity")
    ResponseEntity<List<Integer>> getProductsQuantity(@RequestBody ProductQuantityRequest productQuantityRequest);


    @PutMapping("/reduce-quantity/{id}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable long id, @RequestParam long quantity
    );
}
