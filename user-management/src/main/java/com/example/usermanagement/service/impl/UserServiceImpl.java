package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.product.ProductResponse;
import com.example.usermanagement.dto.user.UserProductResponse;
import com.example.usermanagement.dto.user.UserResponse;
import com.example.usermanagement.external.ProductClient;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductClient productClient;


    @Override
    public UserResponse findUserWithId(long userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new RuntimeException("No user with id"));

        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserProductResponse findUserProduct(long userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(
                        ()-> new RuntimeException("No user with id")
                );

        long productId = 1;

        ProductResponse productResponse = productClient.findProductWithId(productId).getBody();

        return UserProductResponse.builder()
                .userId(user.getId())
                .productResponse(productResponse)
                .build();


    }

    @Override
    public String findCreditCardDetail(long userId) {

        final User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new RuntimeException("No user with id"));

        return user.getCreditCardId();
    }

    @Override
    public User findUserDetails(long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id "));
    }
}
