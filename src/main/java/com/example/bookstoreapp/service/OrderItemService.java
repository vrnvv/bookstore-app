package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.orderdto.OrderItemRequestDto;
import com.example.bookstoreapp.dto.orderdto.OrderItemResponseDto;
import com.example.bookstoreapp.model.entity.OrderItem;

public interface OrderItemService extends
        BasicService<OrderItemRequestDto, OrderItemResponseDto, Long> {
    OrderItemResponseDto findOrderItemByOrderIdAndId(Long orderId,
                                                     Long itemId);

    OrderItem save(OrderItem orderItem);
}
