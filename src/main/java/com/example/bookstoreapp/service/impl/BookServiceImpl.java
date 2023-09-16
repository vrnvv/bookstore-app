package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.request.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.BookDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.service.BookService;
import com.example.bookstoreapp.service.mapper.BookMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        Book book = bookRepository.save(bookMapper.mapToModel(bookDto));
        return bookMapper.mapToDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto get(Long id) {
        return bookMapper.mapToDto(
                bookRepository.get(id).orElseThrow(
                        () -> new RuntimeException("Can't get book by id " + id)));
    }
}
