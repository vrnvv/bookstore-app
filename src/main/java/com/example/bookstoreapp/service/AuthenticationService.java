package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.request.UserLoginRequestDto;
import com.example.bookstoreapp.dto.response.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto request);
}
