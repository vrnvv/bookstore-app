package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestDto;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestWithoutBookId;
import com.example.bookstoreapp.dto.shoppingcartdto.ShoppingCartResponseDto;
import com.example.bookstoreapp.model.entity.ShoppingCart;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ShoppingCartService extends
        BasicService<CartItemRequestDto, ShoppingCartResponseDto, Long> {

    List<ShoppingCartResponseDto> findUserShoppingCart(Pageable pageable);

    ShoppingCartResponseDto addItemToCart(CartItemRequestDto requestDto);

    ShoppingCartResponseDto update(Long id, CartItemRequestWithoutBookId cartItemRequestDto);

    ShoppingCart findCartByUserId();
}
