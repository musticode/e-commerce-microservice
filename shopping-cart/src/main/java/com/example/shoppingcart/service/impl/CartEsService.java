package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.dto.CartDto;
import com.example.shoppingcart.dto.CartRequest;
import com.example.shoppingcart.exception.CartNotFoundException;
import com.example.shoppingcart.model.es.CartEs;
import com.example.shoppingcart.model.es.CartItemEs;
import com.example.shoppingcart.model.postgre.Cart;
import com.example.shoppingcart.model.postgre.CartItem;
import com.example.shoppingcart.repository.es.CartEsRepository;
import com.example.shoppingcart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartEsService {

    private final ModelMapper modelMapper;
    private final CartEsRepository cartEsRepository;



    public CartEs findCartInEs(String cartId){

        CartEs cartEs = cartEsRepository
                .findById(cartId)
                .orElseThrow(
                        ()-> new CartNotFoundException("Cart not found in es")
                );

        return cartEs;
    }


    public CartEs saveCart(CartEs cartEs){
        cartEsRepository.save(cartEs);
        log.info("Saved {}", cartEs);
        return cartEs;
    }




    private CartItemEs mapToEsEntity(CartItem cartItem) {
        return modelMapper.map(cartItem, CartItemEs.class);
    }

    private List<CartItemEs> mapToCartEsList(List<CartItem> cartItems) {
        return cartItems
                .stream()
                .map(item -> modelMapper.map(item, CartItemEs.class))
                .collect(Collectors.toList());
    }

}
