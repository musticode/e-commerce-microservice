package com.example.usermanagement.controller;

import com.example.usermanagement.dto.product.ProductResponse;
import com.example.usermanagement.dto.user.UserProductResponse;
import com.example.usermanagement.dto.user.UserResponse;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.usermanagement.constant.UserConstant.USER_ENDPOINT;
import static com.example.usermanagement.constant.UserConstant.USER_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_ENDPOINT)
public class UserController {



    private final UserService userService;

    @GetMapping(USER_ID)
    public ResponseEntity<UserResponse> findUserById(@PathVariable long userId){
        UserResponse userResponse = userService.findUserWithId(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}/products")
    public ResponseEntity<UserProductResponse> userProduct(@PathVariable long userId){
        UserProductResponse response = userService.findUserProduct(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/credit-card/{userId}")
    public ResponseEntity<String> findUserCreditCard(@PathVariable long userId){
        return new ResponseEntity<>(userService.findCreditCardDetail(userId), HttpStatus.OK);
    }

    @GetMapping("/details/{userId}")
    public ResponseEntity<User> findUserDetailsById(@PathVariable long userId){
        return new ResponseEntity<>(userService.findUserDetails(userId), HttpStatus.OK);
    }

//
//    @PostMapping
//    public ResponseEntity<?> browseProducts(@RequestBody BrowseProductRequest product){
//        List<ProductResponse> productResponses = userService.browseProducts(product);
//        return new ResponseEntity<>(productResponses, HttpStatus.OK);
//    }


}
