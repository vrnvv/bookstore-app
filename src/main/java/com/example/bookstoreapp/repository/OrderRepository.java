package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.entity.Order;
import com.example.bookstoreapp.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems")
    List<Order> getAllByUser(Pageable pageable, User user);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findById(Long id);
}
