package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.bookdto.BookDto;
import com.example.bookstoreapp.dto.bookdto.BookRequestDto;
import com.example.bookstoreapp.dto.bookdto.BookResponseDtoWithoutCategories;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.Category;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.CategoryRepository;
import com.example.bookstoreapp.service.AbstractService;
import com.example.bookstoreapp.service.BookService;
import com.example.bookstoreapp.service.mapper.BookMapper;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl extends AbstractService<Book,
        BookRequestDto, BookDto,
        BookMapper> implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;
    private final CategoryRepository categoryRepository;

    protected JpaRepository<Book, Long> getRepository() {
        return repository;
    }

    protected BookMapper getMapper() {
        return mapper;
    }

    @Override
    public BookDto save(BookRequestDto requestDto) {
        Category category = categoryRepository.findCategoryByName(
                requestDto.getCategoryName())
                .orElseThrow(() -> new RuntimeException(
                        "Can't get book by name " + requestDto.getCategoryName()));
        Book book = mapper.toEntity(requestDto);
        book.setCategories(Set.of(category));
        BookDto bookDto = mapper.toDto(repository.save(book));
        mapper.setCategoryIds(bookDto, book);
        return bookDto;
    }

    @Override
    public BookDto update(Long id, BookRequestDto updatedBookDto) {
        Category category = categoryRepository.findCategoryByName(
                        updatedBookDto.getCategoryName())
                .orElseThrow(() -> new RuntimeException(
                        "Can't get book by name " + updatedBookDto.getCategoryName()));
        Book book = mapper.toEntity(updatedBookDto);
        book.setCategories(Set.of(category));
        Optional<Book> bookFromDb = repository.findById(id);
        return getMapper().toDto(bookFromDb.map(
                b -> super.saveAndReturnSavedEntity(book, b))
                .orElseThrow(
                        () -> new RuntimeException(
                                "Can't update: book by id " + id)));
    }

    @Override
    public List<BookDto> searchBook(String search, Pageable pageable) {
        return repository.searchBook(search, pageable)
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDtoWithoutCategories> findAllByCategoryId(Long categoryId) {

        return repository.findAllByCategory(categoryId)
                .stream().map(mapper::toDtoWithoutCategories)
                .collect(Collectors.toList());
    }
}
