package com.example.productcatalog.service;

import com.example.productcatalog.dto.ProductRequest;
import com.example.productcatalog.dto.ProductResponse;
import com.example.productcatalog.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponse findProductById(long productId);

    Product saveProduct(ProductRequest productRequest);

    List<Product> findAllProducts();

    Product findProductWithId(long productId);

    Long findQuantity(long productId);
    List<Product> findProductsByCategory(long categoryId);
}
