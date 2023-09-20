package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("FROM Book b WHERE "
            + "b.author LIKE CONCAT('%', :search, '%')"
            + "OR b.title LIKE CONCAT('%', :search, '%')"
            + "OR b.isbn LIKE CONCAT('%', :search, '%')")
    List<Book> searchBook(String search, Pageable pageable);
}
