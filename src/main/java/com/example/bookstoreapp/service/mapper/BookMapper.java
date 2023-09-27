package com.example.bookstoreapp.service.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.bookdto.BookDto;
import com.example.bookstoreapp.dto.bookdto.BookRequestDto;
import com.example.bookstoreapp.dto.bookdto.BookRequestWithoutCategory;
import com.example.bookstoreapp.dto.bookdto.BookResponseDtoWithoutCategories;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.Category;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper extends BasicMapper<Book,
        BookRequestDto, BookDto> {

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Book toEntity(BookRequestDto bookRequestDto);

    @Mapping(target = "categoryId", ignore = true)
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Book bookDtoWithoutCategoryIds(BookRequestWithoutCategory bookRequestDto);

    BookResponseDtoWithoutCategories toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        List<Long> categoryIds = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList());

        bookDto.setCategoryId(categoryIds);
    }
}
