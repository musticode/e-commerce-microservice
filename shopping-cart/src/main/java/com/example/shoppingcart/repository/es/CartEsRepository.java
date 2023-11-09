package com.example.shoppingcart.repository.es;

import com.example.shoppingcart.model.es.CartEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEsRepository extends ElasticsearchRepository<CartEs, String> {
}
