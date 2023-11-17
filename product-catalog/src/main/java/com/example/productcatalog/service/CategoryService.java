package com.example.productcatalog.service;

import com.example.productcatalog.dto.CategoryRequest;
import com.example.productcatalog.dto.CategoryResponse;
import com.example.productcatalog.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse findCategoryWithId(long categoryId);

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    Category findCategoryById(long categoryId);
    List<Category> findCategories();
}
