package com.example.bookstoreapp.dto.orderdto;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long bookId;
    private Integer quantity;
}
