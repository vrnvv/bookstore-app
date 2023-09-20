package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.request.CreateBookRequestDto;
import com.example.bookstoreapp.dto.response.BookDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.service.BookService;
import com.example.bookstoreapp.service.mapper.BookMapper;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    public BookDto update(Long id, CreateBookRequestDto updatedBookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book bookToUpdate = optionalBook.get();
            bookToUpdate.setTitle(updatedBookDto.getTitle());
            bookToUpdate.setAuthor(updatedBookDto.getAuthor());
            bookToUpdate.setIsbn(updatedBookDto.getIsbn());
            bookToUpdate.setPrice(updatedBookDto.getPrice());
            bookToUpdate.setDescription(updatedBookDto.getDescription());
            bookToUpdate.setCoverImage(updatedBookDto.getCoverImage());

            Book updatedBook = bookRepository.save(bookToUpdate);
            return bookMapper.mapToDto(updatedBook);
        } else {
            throw new NoSuchElementException("Book not found with id: " + id);
        }
    }

    @Override
    public BookDto get(Long id) {
        return bookMapper.mapToDto(
                bookRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Can't get book by id " + id)));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> searchBook(String search) {
        return bookRepository.searchBook(search)
                .stream().map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
