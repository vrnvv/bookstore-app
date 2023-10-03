package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.entity.ShoppingCart;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = {"cartItems", "user"})
    Page<ShoppingCart> findAllByUserId(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"cartItems", "user"})
    Optional<ShoppingCart> findByUserId(Long id);
}
