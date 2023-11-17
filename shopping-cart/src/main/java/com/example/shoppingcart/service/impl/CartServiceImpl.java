package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.converter.cart.CartConverter;
import com.example.shoppingcart.converter.cart.CartItemConverter;
import com.example.shoppingcart.dto.CartDto;
import com.example.shoppingcart.dto.CartItemDto;
import com.example.shoppingcart.dto.CartRequest;
import com.example.shoppingcart.exception.CartNotFoundException;
import com.example.shoppingcart.model.es.CartEs;
import com.example.shoppingcart.model.es.CartItemEs;
import com.example.shoppingcart.model.postgre.Cart;
import com.example.shoppingcart.model.postgre.CartItem;
import com.example.shoppingcart.repository.CartRepository;
import com.example.shoppingcart.repository.ItemRepository;
import com.example.shoppingcart.service.CartItemService;
import com.example.shoppingcart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
//    private final CartEsService cartEsService;
    private final CartItemService cartItemService;


    public Cart createCart(CartDto cartDto){
        List<CartItem> cartItemList = convertCartItemDtoListToEntityList(cartDto.getItemList());
        Cart cart = Cart.builder()
                .name(cartDto.getName())
                .userId(cartDto.getUserId())
                //.itemList(cartItemList)
                .itemCount(cartDto.getItemCount())
                .isEmpty(cartDto.isEmpty())
                .note(cartDto.getNote())
                .totalPrice(cartDto.getTotalPrice())
                .requiresShipping(cartDto.isRequiresShipping())
                .totalDiscount(cartDto.getTotalDiscount())
                .build();

        cartRepository.save(cart);
        cart.setItemList(cartItemList);
        cartRepository.save(cart);
        return cart;
    }


    @Override
    public Cart findCartWithId(long cartId) {

        if (cartId <= 0) {
            throw new CartNotFoundException("No cart with id " + cartId);
        }

        Cart cart = cartRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("No cart with " + cartId));



        return cart;
    }

    @Override
    public CartDto addItem(CartDto cartRequest) {

        // map item to items
        List<CartItem> cartItems = cartRequest
                .getItemList()
                .stream()
                .map(item -> modelMapper.map(item, CartItem.class))
                .collect(Collectors.toList());

        List<Double> prices = new ArrayList<>();
        cartItems.forEach(item -> prices.add(item.getPrice()));

        final double totalPrice = totalPriceSum(prices);


        Cart cart = CartConverter.convertCartToEntity(cartRequest);
        Cart addedCart = cartRepository.save(cart);

        addedCart.getItemList().forEach(cartItem -> cartItem.setCart(addedCart));




    return modelMapper.map(addedCart, CartDto.class);
//        cartEsService.saveCart(CartEs
//                .builder()
//                .name(cart.getName())
//                .itemCount(cart.getItemCount())
//                .isEmpty(cart.isEmpty())
//                .note(cart.getNote())
//                .totalPrice(totalPrice)
//                .requiresShipping(cart.isRequiresShipping())
//                .totalDiscount(cart.getTotalDiscount())
//                .itemList(cart.getItemList().stream().map(item -> modelMapper.map(item, CartItemEs.class)).collect(Collectors.toList()))
//                .build()
//        );


//        return CartConverter.convertCartToDto(cart);
    }

    @Override
    public String deleteCartWithId(long cartId) {
        Cart cart = findCartWithId(cartId);
        cartRepository.delete(cart);
        return "DELETED";
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    private double totalPriceSum(List<Double> priceList){
        double sum = 0;
        if (!priceList.isEmpty()){
            for (Double price : priceList){
                sum += price;
            }
        }
        return sum;
    }


    private List<CartItem> convertCartItemDtoListToEntityList(List<CartItemDto> cartItemDtoList){
        return cartItemDtoList.stream().map(cartItemDto -> modelMapper.map(cartItemDto, CartItem.class)).collect(Collectors.toList());
    }



}
