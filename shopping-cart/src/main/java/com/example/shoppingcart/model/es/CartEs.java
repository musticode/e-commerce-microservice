package com.example.shoppingcart.model.es;

import com.example.shoppingcart.model.postgre.CartItem;
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

import java.io.Serializable;
import java.util.List;
import java.util.Random;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "carts")
public class CartEs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private int itemCount;

    @Field(type = FieldType.Boolean)
    private boolean isEmpty;

    @Field(type = FieldType.Text)
    private String note;

    @Field(type = FieldType.Double)
    private double totalPrice;

    @Field(type = FieldType.Boolean)
    private boolean requiresShipping;

    @Field(type = FieldType.Double)
    private double totalDiscount;

    @Field(type = FieldType.Nested, includeInParent = false)
    private List<CartItemEs> itemList;
}
