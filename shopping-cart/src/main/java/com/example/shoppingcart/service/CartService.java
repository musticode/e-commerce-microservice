package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.CartDto;
import com.example.shoppingcart.dto.CartRequest;
import com.example.shoppingcart.dto.CartResponse;
import com.example.shoppingcart.model.postgre.Cart;

import java.util.List;

public interface CartService {

    CartResponse addItemToCart(CartRequest cartRequest);

    CartResponse viewCart(long cartId);

    CartResponse updateCartItem(long cartId, CartRequest cartRequest);
}
