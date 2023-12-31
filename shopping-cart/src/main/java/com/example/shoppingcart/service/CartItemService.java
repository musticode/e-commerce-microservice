package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.CartItemDto;
import com.example.shoppingcart.model.postgre.CartItem;

import java.util.List;

public interface CartItemService {
    CartItemDto getItemWithId(long id);
    CartItemDto saveItem(CartItemDto cartItemDto);
    List<CartItemDto> findAllItems();
    CartItem addItem(CartItem cartItem);
}
