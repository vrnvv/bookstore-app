package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.request.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.response.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserRegistrationRequestDto request);
}
