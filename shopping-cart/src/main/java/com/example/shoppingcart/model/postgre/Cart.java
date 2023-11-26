package com.example.shoppingcart.model.postgre;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long cartId;

    private int totalItems;

    private String name;

    private long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartItem> itemList;

    @ColumnDefault("0")
    private int itemCount;

    @ColumnDefault("false")
    private boolean isEmpty;

    private String note;

    @Column
    @ColumnDefault("0.0")
    private double totalPrice;

    @ColumnDefault("false")
    private boolean requiresShipping;

    @ColumnDefault("0.0")
    private double totalDiscount;

}
