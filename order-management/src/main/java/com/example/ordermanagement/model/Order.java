package com.example.ordermanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = true)
    private long cartId;
    private Date cancelledAt;
    private String cancelReason;
    private double totalPrice;
    private long userId;
    private long sellerId;
    private long buyerId;
    private String shippingAddress;
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderLineItem> orderLineItems;
}
