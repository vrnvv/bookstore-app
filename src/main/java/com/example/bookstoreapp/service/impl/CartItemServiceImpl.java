package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestDto;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemResponseDto;
import com.example.bookstoreapp.exception.EntityNotFoundException;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.CartItem;
import com.example.bookstoreapp.model.entity.ShoppingCart;
import com.example.bookstoreapp.model.entity.User;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.CartItemRepository;
import com.example.bookstoreapp.repository.ShoppingCartRepository;
import com.example.bookstoreapp.service.AbstractService;
import com.example.bookstoreapp.service.CartItemService;
import com.example.bookstoreapp.service.UserService;
import com.example.bookstoreapp.service.mapper.CartItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl extends AbstractService<CartItem,
        CartItemRequestDto, CartItemResponseDto,
        CartItemMapper> implements CartItemService {
    private final CartItemRepository repository;
    private final CartItemMapper mapper;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;

    protected JpaRepository<CartItem, Long> getRepository() {
        return repository;
    }

    protected CartItemMapper getMapper() {
        return mapper;
    }

    @Override
    public CartItemResponseDto save(CartItemRequestDto requestDto) {
        CartItem cartItem = getCartItem(requestDto);
        return mapper.toDto(repository.save(cartItem));
    }

    public CartItem getCartItem(CartItemRequestDto requestDto) {
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find book by id: "
                                + requestDto.getBookId()));
        User user = userService.getUser();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find shopping cart"
                        + " by user id: " + user.getId()));
        return mapper.toEntity(requestDto, book, shoppingCart);
    }

    public CartItem saveAndGetCartItem(CartItemRequestDto requestDto) {
        return repository.save(getCartItem(requestDto));
    }
}
