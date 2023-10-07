package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.orderdto.OrderItemResponseDto;
import com.example.bookstoreapp.dto.orderdto.OrderResponseDto;
import com.example.bookstoreapp.dto.orderdto.PlaceOrderRequestDto;
import com.example.bookstoreapp.dto.orderdto.UpdateStatusRequestDto;
import com.example.bookstoreapp.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Managing orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get orders history",
            description = "Get a list of orders history for specified user")
    public ResponseEntity<List<OrderResponseDto>> getOrderHistory(
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrderHistory(pageable));
    }

    @PostMapping
    @Operation(summary = "Create an order",
            description = "Add a new order with the provided details")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderResponseDto> placeOrder(
            @RequestBody PlaceOrderRequestDto placeOrderRequestDto) {
        return ResponseEntity.ok(orderService.placeOrder(placeOrderRequestDto));
    }

    @PatchMapping("/{orderId}")
    @Operation(summary = "Update order status. <ONLY FOR ADMIN ROLE>",
            description = "Update order status to DELIVERED, PENDING or COMPLETED")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrderStatus(@PathVariable Long orderId,
                                  @RequestBody UpdateStatusRequestDto updateOrderStatusDto) {
        orderService.update(orderId, updateOrderStatusDto);
    }

    @GetMapping("/{orderId}/items")
    @Operation(summary = "Get all order items",
            description = "Get all order items for a specific order")
    public ResponseEntity<List<OrderItemResponseDto>> getAllOrderItems(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderItems(orderId));
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get order item by Id",
            description = "Get order item in the specific order")
    public ResponseEntity<OrderItemResponseDto> getOrderItemById(@PathVariable Long orderId,
                                                                @PathVariable Long itemId) {
        return ResponseEntity.ok(orderService.getOrderItem(orderId, itemId));
    }
}
