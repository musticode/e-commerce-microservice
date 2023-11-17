package com.example.productcatalog.service.impl;

import com.example.productcatalog.dto.CategoryRequest;
import com.example.productcatalog.dto.CategoryResponse;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.repository.CategoryRepository;
import com.example.productcatalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse findCategoryWithId(long categoryId) {

        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(
                        ()-> new RuntimeException("No category found")
                );

        log.info("Category found : {}", category);
        return mapCategoryToResponse(category);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Category category = Category.builder()
                .name(categoryRequest.getName())
                .categoryId(categoryRequest.getCategoryId())
                .build();

        categoryRepository.save(category);

        return mapCategoryToResponse(category);
    }

    @Override
    public Category findCategoryById(long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(
                        ()-> new RuntimeException("No category found")
                );
        return category;
    }
    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }


    private CategoryResponse mapCategoryToResponse(Category category){
        return modelMapper.map(category, CategoryResponse.class);
    }
}
