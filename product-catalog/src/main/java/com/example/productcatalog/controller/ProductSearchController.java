package com.example.productcatalog.controller;

import com.example.productcatalog.model.es.ProductEs;
import com.example.productcatalog.service.impl.ProductEsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.productcatalog.constant.ProductConstant.PRODUCT_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT_ENDPOINT)
public class ProductSearchController {

    private final ProductEsServiceImpl productEsService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductEs>> findProductWithName(@RequestParam String name){
        return new ResponseEntity<>(productEsService.searchProductByProductName(name), HttpStatus.OK);
    }



}
