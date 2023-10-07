package com.example.bookstoreapp.dto.orderdto;

import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.CartItem;
import lombok.Getter;

@Getter
public class OrderItemRequestDto {
    private CartItem cartItem;
    private Book book;
}
