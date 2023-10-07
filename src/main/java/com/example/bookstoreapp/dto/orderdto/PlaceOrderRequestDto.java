package com.example.bookstoreapp.dto.orderdto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class PlaceOrderRequestDto {
    @NotEmpty
    private String shippingAddress;
}
