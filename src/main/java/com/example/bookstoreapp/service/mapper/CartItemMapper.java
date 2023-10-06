package com.example.bookstoreapp.service.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestDto;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemResponseDto;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.CartItem;
import com.example.bookstoreapp.model.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper extends BasicMapper<CartItem,
        CartItemRequestDto, CartItemResponseDto> {
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shoppingCart", ignore = true)
    @Mapping(target = "book", ignore = true)
    CartItem toEntity(CartItemRequestDto requestDto);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    CartItem toEntity(CartItemRequestDto requestDto, Book book,
                     ShoppingCart shoppingCart);

    @Mapping(target = "bookId", source = "cartItem.book.id")
    @Mapping(target = "bookTitle", source = "cartItem.book.title")
    CartItemResponseDto toDto(CartItem cartItem);
}
