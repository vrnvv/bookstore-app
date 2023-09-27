package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.userdto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.userdto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserRegistrationRequestDto request);

}
