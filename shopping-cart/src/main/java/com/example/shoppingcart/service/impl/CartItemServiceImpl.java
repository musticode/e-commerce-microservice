package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.converter.cart.CartItemConverter;
import com.example.shoppingcart.dto.CartItemDto;
import com.example.shoppingcart.exception.ItemNotFoundException;
import com.example.shoppingcart.model.postgre.Cart;
import com.example.shoppingcart.model.postgre.CartItem;
import com.example.shoppingcart.repository.ItemRepository;
import com.example.shoppingcart.service.CartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;



    public CartItem addItemToCart(CartItem cartItem){
        return itemRepository.save(cartItem);
    }

    @Override
    public CartItem addItem(CartItem cartItem){
        return itemRepository.save(cartItem);
    }


    @Override
    public CartItemDto getItemWithId(long id) {

        CartItem cartItem = itemRepository.findById(id).orElseThrow(()-> new ItemNotFoundException("Item not found"));

        return modelMapper.map(cartItem, CartItemDto.class);
    }

    @Override
    public CartItemDto saveItem(CartItemDto cartItemDto) {


        return null;
    }

    @Override
    public List<CartItemDto> findAllItems() {
        return itemRepository.findAll().stream().map(CartItemConverter::convertCartItemToDTO).collect(Collectors.toList());
    }


}
