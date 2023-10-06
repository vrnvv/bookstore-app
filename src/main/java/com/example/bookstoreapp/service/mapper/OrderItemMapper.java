package com.example.bookstoreapp.service.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.orderdto.OrderItemRequestDto;
import com.example.bookstoreapp.dto.orderdto.OrderItemResponseDto;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.CartItem;
import com.example.bookstoreapp.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper extends BasicMapper<OrderItem,
        OrderItemRequestDto, OrderItemResponseDto> {
    @Mapping(target = "bookId", source = "orderItem.book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", source = "book.price")
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem cartItemToOrderItem(CartItem cartItem, Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", source = "book.price")
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "quantity", ignore = true)
    OrderItem toEntity(OrderItemRequestDto requestDto);
}
