package com.example.bookstoreapp.service.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestDto;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestWithoutBookId;
import com.example.bookstoreapp.dto.shoppingcartdto.ShoppingCartResponseDto;
import com.example.bookstoreapp.model.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper extends BasicMapper<ShoppingCart,
        CartItemRequestDto, ShoppingCartResponseDto> {
    @Mapping(target = "cartItems", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    ShoppingCart toEntity(CartItemRequestDto request);

    @Mapping(target = "cartItems", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    ShoppingCart toEntity(CartItemRequestWithoutBookId request);

    @Mapping(target = "cartItems", source = "cartItems")
    @Mapping(target = "userId", source = "user.id")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
