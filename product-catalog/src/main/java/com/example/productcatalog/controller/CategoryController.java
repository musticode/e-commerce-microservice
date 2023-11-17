package com.example.productcatalog.controller;

import com.example.productcatalog.dto.CategoryRequest;
import com.example.productcatalog.dto.CategoryResponse;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.productcatalog.constant.CategoryConstant.CATEGORY_ENDPOINT;
import static com.example.productcatalog.constant.CategoryConstant.CATEGORY_ID;
import static com.example.productcatalog.constant.ProductConstant.PRODUCT_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(CATEGORY_ENDPOINT)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(CATEGORY_ID)
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable long categoryId){
        CategoryResponse categoryResponse = categoryService.findCategoryWithId(categoryId);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories(){
        return new ResponseEntity<>(categoryService.findCategories(), HttpStatus.OK);
    }




}
