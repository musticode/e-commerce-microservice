package com.example.productcatalog.service.impl;

import com.example.productcatalog.exception.ProductNotFoundException;
import com.example.productcatalog.model.es.ProductEs;
import com.example.productcatalog.repository.es.ProductEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductEsServiceImpl {

    private final ProductEsRepository productEsRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    public ProductEs saveProduct(final ProductEs productEs) {

        if (productEs.getPrice() < 0) {
            throw new ProductNotFoundException("price can not be lower than 0");
        }

        if (productEs.getQuantity() < 0) {
            throw new ProductNotFoundException("Not enough product quantity");
        }

        ProductEs newProductEs = ProductEs.builder()
                .name(productEs.getName())
                .quantity(productEs.getQuantity())
                .price(productEs.getPrice())
                .description(productEs.getDescription())
                .available(productEs.getAvailable())
                .sellerId(productEs.getSellerId())
                .manufacturer(productEs.getManufacturer())
                .category(productEs.getCategory())
                .build();

        productEsRepository.save(newProductEs);
        return newProductEs;
    }

    public ProductEs updateProductDetails(String productId, ProductEs productEs){

        ProductEs foundedProduct = productEsRepository
                .findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product not found")
                );

        foundedProduct.setName(productEs.getName());
        foundedProduct.setQuantity(productEs.getQuantity());
        foundedProduct.setPrice(productEs.getPrice());
        foundedProduct.setDescription(productEs.getDescription());
        foundedProduct.setAvailable(productEs.getAvailable());
        foundedProduct.setSellerId(productEs.getSellerId());
        foundedProduct.setManufacturer(productEs.getManufacturer());
        foundedProduct.setCategory(productEs.getCategory());

        return productEsRepository.save(foundedProduct);
    }

    public List<ProductEs> searchProductByProductName(String productName){
        return productEsRepository.findByName(productName);
    }

    public List<ProductEs> searchProductByDescription(String description){
        return productEsRepository.findByDescriptionContaining(description);
    }


    public List<ProductEs> searchProductByNameContains(String name){
        return productEsRepository.findByNameContaining(name);
    }


    public List<ProductEs> searchProductByManufacturerAndCategory(String manufacturer, String category){
        return productEsRepository.findByManufacturerAndCategory(manufacturer, category);
    }

    public ProductEs findProductWithId(String productId) {
        return productEsRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found with "+ productId ));
    }

    public List<ProductEs> searchProductByCategoryName(String categoryName) {
        return productEsRepository.findByCategory(categoryName);
    }

}
