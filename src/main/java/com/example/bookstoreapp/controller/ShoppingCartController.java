package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestDto;
import com.example.bookstoreapp.dto.shoppingcartdto.CartItemRequestWithoutBookId;
import com.example.bookstoreapp.dto.shoppingcartdto.ShoppingCartResponseDto;
import com.example.bookstoreapp.service.CartItemService;
import com.example.bookstoreapp.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/cart")
@Tag(name = "ShoppingCart", description = "Managing shopping carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @GetMapping
    @Operation(summary = "Get user shopping cart")
    public ResponseEntity<List<ShoppingCartResponseDto>> getAll(
            @PageableDefault(size = 25, page = 0) Pageable pageable) {
        List<ShoppingCartResponseDto> cart = shoppingCartService.findUserShoppingCart(pageable);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    @Operation(summary = "Add book to the shopping cart")
    public ResponseEntity<ShoppingCartResponseDto> addItemToCart(
            @RequestBody @Valid CartItemRequestDto cartItemRequestDto) {
        ShoppingCartResponseDto cart = shoppingCartService.addItemToCart(cartItemRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update quantity of a book in the shopping cart")
    public ResponseEntity<ShoppingCartResponseDto> updateCartItem(
            @PathVariable Long cartItemId,
            @RequestBody @Valid CartItemRequestWithoutBookId cartItemRequestDto) {
        ShoppingCartResponseDto cart = shoppingCartService.update(cartItemId, cartItemRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a book from the shopping cart")
    public void deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.delete(cartItemId);
    }
}
