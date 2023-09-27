package com.example.bookstoreapp.dto.userdto;

import com.example.bookstoreapp.validation.FieldsValueMatch;
import com.example.bookstoreapp.validation.ValidEmail;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserLoginRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 20)
    private String password;
    private String repeatPassword;
}
