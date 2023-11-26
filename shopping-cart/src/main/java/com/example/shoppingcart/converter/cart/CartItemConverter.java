package com.example.shoppingcart.converter.cart;

import com.example.shoppingcart.converter.Converter;
import com.example.shoppingcart.dto.CartItemDto;
import com.example.shoppingcart.model.postgre.CartItem;

public class CartItemConverter extends Converter<CartItemDto, CartItem> {

    public CartItemConverter(){
        super(CartItemConverter::convertCartItemToEntity, CartItemConverter::convertCartItemToDTO);
    }

    public static CartItemDto convertCartItemToDTO(CartItem cartItem) {
        return CartItemDto.builder()
                .id(cartItem.getId())
                .name(cartItem.getName())
                .price(cartItem.getPrice())
//                .finalPrice(cartItem.getFinalPrice())
//                .message(cartItem.getMessage())
//                .productId(cartItem.getProductId())
                .quantity(cartItem.getQuantity())
//                .sku(cartItem.getSku())
//                .title(cartItem.getTitle())
//                .vendor(cartItem.getVendor())
                .build();
    }

    public static CartItem convertCartItemToEntity(CartItemDto cartItemDto) {
        return CartItem.builder()
                .name(cartItemDto.getName())
                .price(cartItemDto.getPrice())
//                .finalPrice(cartItemDto.getFinalPrice())
//                .message(cartItemDto.getMessage())
//                .productId(cartItemDto.getProductId())
                .quantity(cartItemDto.getQuantity())
//                .sku(cartItemDto.getSku())
//                .title(cartItemDto.getTitle())
//                .vendor(cartItemDto.getVendor())
                .build();
    }

}
