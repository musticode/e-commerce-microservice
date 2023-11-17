package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.CartDto;
import com.example.shoppingcart.dto.CartRequest;
import com.example.shoppingcart.model.postgre.Cart;

import java.util.List;

public interface CartService {
    Cart findCartWithId(long cartId);

    CartDto addItem(CartDto cartItem);

    String deleteCartWithId(long cartId);

    List<Cart> getAllCarts();
}
