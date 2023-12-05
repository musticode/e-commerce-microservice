package com.example.shoppingcart.controller;

import com.example.shoppingcart.dto.*;
import com.example.shoppingcart.model.postgre.Cart;
import com.example.shoppingcart.service.impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.shoppingcart.constant.CartConstant.CART_ENDPOINT;
import static com.example.shoppingcart.constant.CartConstant.CART_ID;


/**
 *  including adding/removing items, quantity adjustments, and viewing the cart contents.
 * */

@RestController
@RequiredArgsConstructor
@RequestMapping(CART_ENDPOINT)
public class CartController {



    private final CartServiceImpl cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> viewCart(@PathVariable long cartId){
        return new ResponseEntity<>(cartService.viewCart(cartId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addOrCreateItemToUserCart(@RequestBody CartRequest cartRequest){
        return new ResponseEntity<>(cartService.addItemToCart(cartRequest), HttpStatus.CREATED);
    }

    @PostMapping("/update-cart/{cartId}")
    public ResponseEntity<CartResponse> updateCartWithId(@PathVariable long cartId, @RequestBody CartRequest cartRequest){
        return new ResponseEntity<>(cartService.updateCartItem(cartId, cartRequest), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponse> getUserCart(@PathVariable long userId){
        return new ResponseEntity<>(cartService.findUserCartWithUserId(userId), HttpStatus.OK);
    }










}
