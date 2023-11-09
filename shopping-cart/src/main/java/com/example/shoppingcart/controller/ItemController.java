package com.example.shoppingcart.controller;

import com.example.shoppingcart.dto.CartItemDto;
import com.example.shoppingcart.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.shoppingcart.constant.ItemConstant.ITEM_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(ITEM_ENDPOINT)
public class ItemController {

    private final CartItemService cartItemService;

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> findItemWithId(@PathVariable long id){
        CartItemDto cartItemDto = cartItemService.getItemWithId(id);
        return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> findItemWithId(){
        List<CartItemDto> cartItemDtoList = cartItemService.findAllItems();
        return new ResponseEntity<>(cartItemDtoList, HttpStatus.OK);
    }
}
