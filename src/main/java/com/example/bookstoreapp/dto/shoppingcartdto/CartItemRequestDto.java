package com.example.bookstoreapp.dto.shoppingcartdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CartItemRequestDto {
    @NotNull
    @Min(1)
    private Long bookId;
    @NotNull
    @Min(1)
    private Integer quantity;
}
