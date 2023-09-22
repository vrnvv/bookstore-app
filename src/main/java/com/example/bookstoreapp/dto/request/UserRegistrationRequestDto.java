package com.example.bookstoreapp.dto.request;

import com.example.bookstoreapp.validation.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRegistrationRequestDto {
    @ValidEmail
    private String email;
    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String shippingAddress;
}
