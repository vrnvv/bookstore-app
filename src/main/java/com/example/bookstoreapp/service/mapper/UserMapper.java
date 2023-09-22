package com.example.bookstoreapp.service.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.request.UserLoginRequestDto;
import com.example.bookstoreapp.dto.request.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.response.UserResponseDto;
import com.example.bookstoreapp.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User mapToModel(UserRegistrationRequestDto userRegistrationRequestDto);

    User mapToModel(UserLoginRequestDto userLoginRequestDto);

    UserResponseDto mapToDto(User user);
}
