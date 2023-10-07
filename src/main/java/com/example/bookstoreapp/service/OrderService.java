package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.orderdto.OrderItemResponseDto;
import com.example.bookstoreapp.dto.orderdto.OrderResponseDto;
import com.example.bookstoreapp.dto.orderdto.PlaceOrderRequestDto;
import com.example.bookstoreapp.dto.orderdto.UpdateStatusRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService extends
        BasicService<PlaceOrderRequestDto, OrderResponseDto, Long> {
    OrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto);

    List<OrderResponseDto> getOrderHistory(Pageable pageable);

    List<OrderItemResponseDto> getOrderItems(Long orderId);

    OrderItemResponseDto getOrderItem(Long orderId, Long itemId);

    void update(Long orderId, UpdateStatusRequestDto updateStatusRequestDto);
}
