package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestDto;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestWithoutBookId;
import com.example.bookstoreapp.dto.shoppingcartdto.ShoppingCartResponseDto;
import com.example.bookstoreapp.exception.EntityNotFoundException;
import com.example.bookstoreapp.model.entity.CartItem;
import com.example.bookstoreapp.model.entity.ShoppingCart;
import com.example.bookstoreapp.model.entity.User;
import com.example.bookstoreapp.repository.ShoppingCartRepository;
import com.example.bookstoreapp.service.AbstractService;
import com.example.bookstoreapp.service.CartItemService;
import com.example.bookstoreapp.service.ShoppingCartService;
import com.example.bookstoreapp.service.UserService;
import com.example.bookstoreapp.service.mapper.ShoppingCartMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl extends AbstractService<ShoppingCart,
        CartItemRequestDto, ShoppingCartResponseDto,
        ShoppingCartMapper> implements ShoppingCartService {
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ShoppingCartRepository repository;
    private final ShoppingCartMapper mapper;

    @Override
    protected JpaRepository<ShoppingCart, Long> getRepository() {
        return repository;
    }

    @Override
    protected ShoppingCartMapper getMapper() {
        return mapper;
    }

    @Override
    public List<ShoppingCartResponseDto> findUserShoppingCart(Pageable pageable) {
        ShoppingCart shoppingCart = findCartByUserId();
        return repository.findAllByUserId(shoppingCart.getUser().getId(), pageable)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public ShoppingCartResponseDto addItemToCart(CartItemRequestDto requestDto) {
        User user = userService.getUser();
        ShoppingCart cart = repository.findByUserId(
                user.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find shopping cart id: "
                                + user.getId()));
        CartItem cartItem = cartItemService.saveAndGetCartItem(requestDto);
        cart.addCartItem(cartItem);
        return mapper.toDto(cart);
    }

    @Override
    public ShoppingCartResponseDto update(
            Long id, CartItemRequestWithoutBookId cartItemRequestDto) {
        ShoppingCart cartFromDb = findCartByUserId();
        for (CartItem cartItem : cartFromDb.getCartItems()) {
            if (cartItem.getId().equals(id)) {
                cartItem.setQuantity(cartItemRequestDto.getQuantity());
            }
        }
        return mapper.toDto(cartFromDb);
    }

    private ShoppingCart findCartByUserId() {
        User user = userService.getUser();
        return repository.findByUserId(
                        user.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find shopping cart id: "
                                + user.getId()));
    }
}
