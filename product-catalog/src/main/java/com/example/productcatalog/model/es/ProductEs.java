package com.example.productcatalog.model.es;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class ProductEs {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Long, name = "quantity")
    private long quantity;

    @Field(type = FieldType.Double, name = "price")
    private double price;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Text, name = "available")
    private String available;

    @Field(type = FieldType.Text, name = "seller_id")
    private String sellerId;

    @Field(type = FieldType.Text, name = "manufacturer")
    private String manufacturer;

    @Field(type = FieldType.Text, name = "category")
    private String category;

}
