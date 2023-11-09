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

        CartEs newCart = CartEs.builder()
                .name(cartEs.getName())
                .itemCount(cartEs.getItemCount())
                .isEmpty(cartEs.isEmpty())
                .itemCount(cartEs.getItemCount())
                .isEmpty(cartEs.isEmpty())
                .note(cartEs.getNote())
                .totalPrice(cartEs.getTotalPrice())
                .requiresShipping(cartEs.isRequiresShipping())
                .totalDiscount(cartEs.getTotalDiscount())
                .itemList(cartEs.getItemList())
                .build();

        cartEsRepository.save(newCart);
        log.info("Saved {}", cartEs);

        return newCart;
    }


    private void saveToEs(CartEs cartEs) {
        if (cartEs == null) {
            throw new RuntimeException("null");
        }
        cartEsRepository.save(cartEs);
        log.info("saved to es {}", cartEs);
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
