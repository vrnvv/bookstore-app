package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.userdto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.userdto.UserResponseDto;
import com.example.bookstoreapp.model.entity.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request);

    User getUser();
}
