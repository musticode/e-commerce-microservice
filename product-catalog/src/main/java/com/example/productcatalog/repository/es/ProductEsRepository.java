package com.example.productcatalog.repository.es;

import com.example.productcatalog.model.es.ProductEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductEsRepository extends ElasticsearchRepository<ProductEs, String> {
    List<ProductEs> findByName(String name);

    List<ProductEs> findByNameContaining(String name);

    List<ProductEs> findByDescriptionContaining(String description);

    List<ProductEs> findByManufacturerAndCategory(String manufacturer, String category);
}
