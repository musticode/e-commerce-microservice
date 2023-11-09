package com.example.usermanagement.service;

import com.example.usermanagement.dto.user.UserProductResponse;
import com.example.usermanagement.dto.user.UserResponse;
import com.example.usermanagement.model.User;

public interface UserService {
    UserResponse findUserWithId(long userId);

    UserProductResponse findUserProduct(long userId);

    String findCreditCardDetail(long userId);

    User findUserDetails(long userId);
}
