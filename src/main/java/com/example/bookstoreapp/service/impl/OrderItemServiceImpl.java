package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.orderdto.OrderItemRequestDto;
import com.example.bookstoreapp.dto.orderdto.OrderItemResponseDto;
import com.example.bookstoreapp.exception.EntityNotFoundException;
import com.example.bookstoreapp.model.entity.OrderItem;
import com.example.bookstoreapp.repository.OrderItemRepository;
import com.example.bookstoreapp.service.AbstractService;
import com.example.bookstoreapp.service.OrderItemService;
import com.example.bookstoreapp.service.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl extends AbstractService<OrderItem,
        OrderItemRequestDto, OrderItemResponseDto,
        OrderItemMapper> implements OrderItemService {
    private final OrderItemMapper mapper;
    private final OrderItemRepository repository;

    @Override
    protected JpaRepository<OrderItem, Long> getRepository() {
        return repository;
    }

    @Override
    protected OrderItemMapper getMapper() {
        return mapper;
    }

    @Override
    public OrderItemResponseDto findOrderItemByOrderIdAndId(Long orderId, Long itemId) {
        return mapper.toDto(repository
                .findOrderItemByOrderIdAndId(orderId, itemId).orElseThrow(() ->
                        new EntityNotFoundException("Can't find item by order id: "
                                + orderId)));
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return repository.save(orderItem);
    }
}
