package com.example.bookstoreapp.repository;

import java.util.List;
import com.example.bookstoreapp.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
