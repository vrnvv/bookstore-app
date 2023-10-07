package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.orderdto.OrderItemResponseDto;
import com.example.bookstoreapp.dto.orderdto.OrderResponseDto;
import com.example.bookstoreapp.dto.orderdto.PlaceOrderRequestDto;
import com.example.bookstoreapp.dto.orderdto.UpdateStatusRequestDto;
import com.example.bookstoreapp.exception.EntityNotFoundException;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.CartItem;
import com.example.bookstoreapp.model.entity.Order;
import com.example.bookstoreapp.model.entity.OrderItem;
import com.example.bookstoreapp.model.entity.ShoppingCart;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.OrderRepository;
import com.example.bookstoreapp.service.AbstractService;
import com.example.bookstoreapp.service.OrderService;
import com.example.bookstoreapp.service.ShoppingCartService;
import com.example.bookstoreapp.service.UserService;
import com.example.bookstoreapp.service.mapper.OrderItemMapper;
import com.example.bookstoreapp.service.mapper.OrderMapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends AbstractService<Order,
        PlaceOrderRequestDto, OrderResponseDto,
        OrderMapper> implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final ShoppingCartService shoppingCartService;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemServiceImpl orderItemService;
    private final BookRepository bookRepository;
    private final UserService userService;

    @Override
    protected JpaRepository<Order, Long> getRepository() {
        return repository;
    }

    @Override
    protected OrderMapper getMapper() {
        return mapper;
    }

    @Override
    public OrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderDto) {
        ShoppingCart shoppingCart = shoppingCartService.findCartByUserId();

        Order order = mapper.toOrderFromCart(shoppingCart);
        order.setShippingAddress(placeOrderDto.getShippingAddress());
        order.setStatus(Order.Status.COMPLETED);
        Order savedOrder = repository.save(order);

        Set<OrderItem> orderItems = getOrderItemsFromCart(shoppingCart);
        orderItems.forEach(orderItem -> orderItem.setOrder(savedOrder));
        savedOrder.setOrderItems(getSavedOrderItems(orderItems));
        completePurchase(shoppingCart);
        return mapper.toDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getOrderHistory(Pageable pageable) {
        return repository.getAllByUser(pageable, userService.getUser())
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemResponseDto> getOrderItems(Long orderId) {
        return getOrderById(orderId)
                .getOrderItems()
                .stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long orderId, UpdateStatusRequestDto updateStatusRequestDto) {
        Order orderFromDb = getOrderById(orderId);
        orderFromDb.setStatus(updateStatusRequestDto.getStatus());
        repository.save(orderFromDb);
    }

    @Override
    public OrderItemResponseDto getOrderItem(Long orderId, Long itemId) {
        return orderItemService.findOrderItemByOrderIdAndId(orderId, itemId);
    }

    private Set<OrderItem> getOrderItemsFromCart(ShoppingCart shoppingCart) {

        return shoppingCart.getCartItems()
                .stream()
                .map(cartItem -> orderItemMapper.cartItemToOrderItem(cartItem,
                        getBookFromCartItem(cartItem)))
                .collect(Collectors.toSet());
    }

    private Book getBookFromCartItem(CartItem cartItem) {
        return bookRepository.findById(cartItem.getBook().getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find book by id: "
                        + cartItem.getBook().getId()));
    }

    private Set<OrderItem> getSavedOrderItems(Set<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(orderItemService::save)
                .collect(Collectors.toSet());
    }

    private void completePurchase(ShoppingCart shoppingCart) {
        shoppingCartService.delete(shoppingCart.getId());
        ShoppingCart shoppingCartNew = new ShoppingCart();
        shoppingCartNew.setUser(shoppingCart.getUser());
        shoppingCartService.save(shoppingCartNew);
    }

    private Order getOrderById(Long orderId) {
        return repository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Can't find order by id: "
                        + orderId));
    }

}
