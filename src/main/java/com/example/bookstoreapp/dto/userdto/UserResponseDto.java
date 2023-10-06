package com.example.bookstoreapp.dto.userdto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
}
