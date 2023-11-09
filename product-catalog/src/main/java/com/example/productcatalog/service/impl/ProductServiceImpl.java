package com.example.productcatalog.service.impl;

import com.example.productcatalog.dto.ProductRequest;
import com.example.productcatalog.dto.ProductResponse;
import com.example.productcatalog.exception.ProductNotFoundException;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.model.es.ProductEs;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.service.CategoryService;
import com.example.productcatalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductEsServiceImpl productEsService;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponse findProductById(long productId) {


        Product foundProduct = productRepository
                .findById(productId)
                .orElseThrow(()->
                        new ProductNotFoundException("Product not found with " + productId)
                );

        return mapToProductResponse(foundProduct);
    }

    @Override
    public Product saveProduct(ProductRequest productRequest) {

        Category category = categoryService
                .findCategoryById(productRequest.getCategoryId());

        Product product = Product.builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .sellerId(productRequest.getSellerId())
                .category(category)
                .build();

        productRepository.save(product);


        ProductEs productEs = ProductEs.builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .manufacturer(productRequest.getManufacturer())
                .category(category.getName())
                .name(productRequest.getName())
                .build();

        productEsService.saveProduct(productEs);

        return product;
    }

    @Override
    public List<ProductResponse> findProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductWithId(long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(()->
                        new ProductNotFoundException("Product not found with " + productId)
                );
    }

    @Override
    public Long findQuantity(long productId) {
        return findProductById(productId).getQuantity();
    }


    private ProductResponse mapToProductResponse(Product product){
        return modelMapper.map(product, ProductResponse.class);
    }

}
