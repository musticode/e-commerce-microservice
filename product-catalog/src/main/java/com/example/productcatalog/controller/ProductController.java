package com.example.productcatalog.controller;

import com.example.productcatalog.dto.ProductRequest;
import com.example.productcatalog.dto.ProductResponse;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.productcatalog.constant.ProductConstant.PRODUCT_ENDPOINT;
import static com.example.productcatalog.constant.ProductConstant.PRODUCT_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT_ENDPOINT)
public class ProductController {

    private final ProductService productService;

    @GetMapping(PRODUCT_ID)
    public ResponseEntity<Product> findProductById(@PathVariable long productId){
        Product product = productService.findProductWithId(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/quantity/{productId}")
    public ResponseEntity<Long> findProductQuantityById(@PathVariable long productId){
        return new ResponseEntity<>(productService.findQuantity(productId), HttpStatus.OK);
    }

    @GetMapping("/all/{categoryId}")
    public ResponseEntity<List<Product>> findProductsByCategoryId(@PathVariable long categoryId){
        return new ResponseEntity<>(productService.findProductsByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest){
        Product addedProduct = productService.saveProduct(productRequest);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

}

