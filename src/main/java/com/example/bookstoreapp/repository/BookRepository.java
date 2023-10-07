package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "Book.categories")
    Page<Book> findAll(Pageable pageable);

    @EntityGraph(value = "Book.categories")
    Optional<Book> findById(Long id);

    @EntityGraph(value = "Book.categories")
    @Query("FROM Book b WHERE "
            + "b.author LIKE CONCAT('%', :search, '%')"
            + "OR b.title LIKE CONCAT('%', :search, '%')"
            + "OR b.isbn LIKE CONCAT('%', :search, '%')")
    List<Book> searchBook(String search, Pageable pageable);

    @Query("FROM Book b JOIN "
            + "b.categories c WHERE c.id = :id")
    List<Book> findAllByCategory(Long id);
}
