package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
