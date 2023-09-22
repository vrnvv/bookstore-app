package com.example.bookstoreapp.service.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.request.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.response.UserResponseDto;
import com.example.bookstoreapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User mapToModel(UserRegistrationRequestDto userRegistrationRequestDto);

    UserResponseDto mapToDto(User user);
}
