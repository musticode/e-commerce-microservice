package com.example.shoppingcart.model.es;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "cart_items")
public class CartItemEs {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String name;

    @Field(type = FieldType.Double)
    private double price;

    @Field(type = FieldType.Double)
    private double finalPrice;

    @Field(type = FieldType.Text)
    private String message;

    @Field(type = FieldType.Long)
    private long productId;

    @Field(type = FieldType.Integer)
    private int quantity;

    @Field(type = FieldType.Text)
    private String sku;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String vendor;
}
