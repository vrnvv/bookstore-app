package com.example.bookstoreapp.service;

import java.util.List;
import com.example.bookstoreapp.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
