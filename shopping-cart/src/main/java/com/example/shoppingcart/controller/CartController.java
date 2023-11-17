package com.example.shoppingcart.controller;

import com.example.shoppingcart.dto.CartDto;
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

    @GetMapping(CART_ID)
    public ResponseEntity<Cart> findCartById(@PathVariable long cartId){
        Cart cart = cartService.findCartWithId(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findAllCarts(){
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }


    @DeleteMapping(CART_ID)
    public ResponseEntity<String> deleteCart(@PathVariable long cartId){
        return new ResponseEntity<>(cartService.deleteCartWithId(cartId), HttpStatus.OK);
    }

    @PostMapping("/add-item-to-cart")
    public ResponseEntity<Cart> addItem(@RequestBody CartDto cart){
        return new ResponseEntity<>(cartService.createCart(cart), HttpStatus.CREATED);
    }












}
