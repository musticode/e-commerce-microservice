package com.example.productcatalog.controller;

import com.example.productcatalog.model.es.ProductEs;
import com.example.productcatalog.service.impl.ProductEsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.productcatalog.constant.ProductConstant.PRODUCT_ENDPOINT;
import static com.example.productcatalog.constant.ProductConstant.PRODUCT_SEARCH_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT_SEARCH_ENDPOINT)
public class ProductSearchController {

    private final ProductEsServiceImpl productEsService;

    @GetMapping
    public ResponseEntity<List<ProductEs>> findProductWithName(@RequestParam String name){
        return new ResponseEntity<>(productEsService.searchProductByProductName(name), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductEs> findProductById(@PathVariable String productId){
        return new ResponseEntity<>(productEsService.findProductWithId(productId), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductEs>> findProductsByCategoryName(@RequestParam String categoryName){
        return new ResponseEntity<>(productEsService.searchProductByCategoryName(categoryName), HttpStatus.OK);
    }



}
