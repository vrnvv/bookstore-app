package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestDto;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemResponseDto;
import com.example.bookstoreapp.model.entity.CartItem;

public interface CartItemService extends
        BasicService<CartItemRequestDto, CartItemResponseDto, Long> {
    CartItem getCartItem(CartItemRequestDto requestDto);

    CartItem saveAndGetCartItem(CartItemRequestDto requestDto);
}
