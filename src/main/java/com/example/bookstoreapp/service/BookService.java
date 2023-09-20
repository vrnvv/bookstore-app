package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.request.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.BookDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll();

    BookDto get(Long id);

    BookDto update(Long id, CreateBookRequestDto updatedBookDto);

    void deleteBook(Long id);

    List<BookDto> searchBook(String search);
}
