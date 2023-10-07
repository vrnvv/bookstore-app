package com.example.bookstoreapp.service.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.orderdto.OrderResponseDto;
import com.example.bookstoreapp.dto.orderdto.PlaceOrderRequestDto;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.CartItem;
import com.example.bookstoreapp.model.entity.Order;
import com.example.bookstoreapp.model.entity.ShoppingCart;
import java.math.BigDecimal;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper extends BasicMapper<Order,
        PlaceOrderRequestDto, OrderResponseDto> {
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "userId", source = "order.user.id")
    OrderResponseDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderDate", expression
            = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "shippingAddress", ignore = true)
    Order toOrderFromCart(ShoppingCart shoppingCart);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "shippingAddress", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderDate", expression
            = "java(java.time.LocalDateTime.now())")
    Order toEntity(PlaceOrderRequestDto requestDto);

    @AfterMapping
    default void setOrderTotal(@MappingTarget Order order,
                               ShoppingCart shoppingCart) {
        order.setTotal(getTotal(shoppingCart));
    }

    private BigDecimal getTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getBook)
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
