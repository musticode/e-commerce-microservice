package com.example.shoppingcart.converter.cart;

import com.example.shoppingcart.converter.Converter;
import com.example.shoppingcart.dto.CartDto;
import com.example.shoppingcart.model.postgre.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class CartConverter extends Converter<com.example.shoppingcart.dto.CartDto, Cart> {

    public CartConverter(){
        super(CartConverter::convertCartToEntity, CartConverter::convertCartToDto);
    }



    public static CartDto convert(Cart cart){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cart, CartDto.class);
    }


    public static com.example.shoppingcart.dto.CartDto convertCartToDto(Cart cart) {
        return com.example.shoppingcart.dto.CartDto.builder()
                .id(cart.getId())
                .name(cart.getName())
                .userId(cart.getUserId())
                .itemList(cart
                        .getItemList()
                        .stream()
                        .map(CartItemConverter::convertCartItemToDTO)
                        .collect(Collectors.toList())
                )
                .itemCount(cart.getItemCount())
                .isEmpty(cart.isEmpty())
                .note(cart.getNote())
                .totalPrice(cart.getTotalPrice())
                .requiresShipping(cart.isRequiresShipping())
                .totalDiscount(cart.getTotalDiscount())
                .build();
    }

    public static Cart convertCartToEntity(com.example.shoppingcart.dto.CartDto cartDto) {
        return Cart.builder()
                .name(cartDto.getName())
                .userId(cartDto.getUserId())
                .itemList(cartDto
                        .getItemList()
                        .stream()
                        .map(CartItemConverter::convertCartItemToEntity)
                        .collect(Collectors.toList())
                )
                .itemCount(cartDto.getItemCount())
                .isEmpty(cartDto.isEmpty())
                .note(cartDto.getNote())
                .totalPrice(cartDto.getTotalPrice())
                .requiresShipping(cartDto.isRequiresShipping())
                .totalDiscount(cartDto.getTotalDiscount())
                .build();
    }

}

