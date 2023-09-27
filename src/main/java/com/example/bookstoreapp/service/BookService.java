package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.bookdto.BookDto;
import com.example.bookstoreapp.dto.bookdto.BookRequestDto;
import com.example.bookstoreapp.dto.bookdto.BookResponseDtoWithoutCategories;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService extends
        BasicService<BookRequestDto, BookDto, Long> {
    List<BookDto> searchBook(String search, Pageable pageable);

    List<BookResponseDtoWithoutCategories> findAllByCategoryId(Long categoryId);

}
