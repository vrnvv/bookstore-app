package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.request.BookRequestDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookRequestDto bookDto) {
        Book book = bookRepository.save(bookMapper.mapToModel(bookDto));
        return bookMapper.mapToDto(book);
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto update(Long id, BookRequestDto updatedBookDto) {
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
    public List<BookDto> searchBook(String search, Pageable pageable) {
        return bookRepository.searchBook(search, pageable)
                .stream().map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
