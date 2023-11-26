package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.converter.cart.CartConverter;
import com.example.shoppingcart.converter.cart.CartItemConverter;
import com.example.shoppingcart.dto.*;
import com.example.shoppingcart.exception.CartNotFoundException;
import com.example.shoppingcart.external.ProductClient;
import com.example.shoppingcart.external.UserClient;
import com.example.shoppingcart.external.model.User;
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

    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final UserClient userClient;
    private final ProductClient productClient;


    @Override
    public CartResponse addItemToCart(CartRequest cartRequest){

        // find user
        User foundUser = userClient.findUserDetailsById(cartRequest.getUserId()).getBody();
        log.info("User {}", foundUser);

        // find product
        Product foundProduct = productClient.findProductById(cartRequest.getProductId()).getBody();
        final long productQuantity = foundProduct.getQuantity();
        log.info("Product{}", foundProduct);

        List<CartItem> cartItemList = new ArrayList<>();


        if (!cartRepository.existsByUserId(foundUser.getId())){

            if (cartRequest.getQuantity() <= productQuantity){

                // TODO create a new cart with founded userId
                Cart cart = Cart.builder()
                        .cartId(1234L)
                        .name(String.valueOf(foundUser.getId()))
                        .itemList(cartItemList)
                        .itemCount(1)
                        .isEmpty(false)
                        .note("Added cart")
                        .totalPrice(calculateTotalPrice(foundProduct.getPrice(), cartRequest.getQuantity()))
                        .requiresShipping(false)
                        .totalDiscount(0)
                        .build();

                cartRepository.save(cart);

                // TODO add founded item to cartItemList
                CartItem cartItem = CartItem.builder()
                        .name(foundProduct.getName())
                        .price(foundProduct.getPrice())
                        .quantity(cartRequest.getQuantity())
                        .subtotal(calculateTotalPrice(foundProduct.getPrice(), cartRequest.getQuantity()))
                        .cart(cart)
                        .build();

                cartItemService.addItem(cartItem);

                // TODO set cartItemList to created cart
                cartItemList.add(cartItem);




                // TODO return cart response
                return CartResponse.builder()
                        .cartId(cart.getCartId())
                        .totalItems(cart.getTotalItems())
                        .totalPrice(cart.getTotalPrice())
                        .item(cart
                                .getItemList()
                                .stream()
                                .map(item -> modelMapper.map(item, CartItemResponse.class ))
                                .collect(Collectors.toList())
                        )
                        .build();

            }

        }else {
            // todo find cart with userId--> user has one cart
            Cart cart = cartRepository.findByUserId(foundUser.getId());

            // todo add item to found cart
            CartItem cartItem = CartItem.builder()
                    .name(foundProduct.getName())
                    .price(foundProduct.getPrice())
                    .quantity(cartRequest.getQuantity())
                    .subtotal(calculateTotalPrice(foundProduct.getPrice(), cartRequest.getQuantity()))
                    .cart(cart)
                    .build();

            // todo update cart
            List<CartItem> foundItemList = cart.getItemList();
            foundItemList.add(cartItem);
            cart.setItemList(foundItemList);

            // todo return cart response
            return CartResponse.builder()
                    .cartId(cart.getCartId())
                    .totalItems(cart.getTotalItems())
                    .totalPrice(cart.getTotalPrice())
                    .item(cart
                            .getItemList()
                            .stream()
                            .map(item -> modelMapper.map(item, CartItemResponse.class ))
                            .collect(Collectors.toList())
                    )
                    .build();


        }

        return null;
    }

    @Override
    public CartResponse viewCart(long cartId) {
        Cart foundCart = findCartWithCartId(cartId);
        return modelMapper.map(foundCart, CartResponse.class);
    }

    @Override
    public CartResponse updateCartItem(long cartId, CartRequest cartRequest) {

        Product product = productClient.findProductById(cartRequest.getProductId()).getBody();

        Cart foundCart = findCartWithCartId(cartId);
        List<CartItem> itemList = foundCart.getItemList();

        CartItem cartItem = CartItem.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(cartRequest.getQuantity())
                .subtotal(product.getPrice())
                .build();

        itemList.add(cartItem);

        foundCart.setItemList(itemList);

        cartRepository.save(foundCart);


        return modelMapper.map(foundCart, CartResponse.class);
    }

    private Cart findCartWithCartId(long cartId){
        return cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException("No cart with id: " + cartId));
    }


    public double calculateTotalPrice(double price, int quantity){
        return price * quantity;
    }

}
