package com.example.shoppingcart.repository;

import com.example.shoppingcart.model.postgre.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<CartItem, Long> {
}
