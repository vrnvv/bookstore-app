package com.example.bookstoreapp.dto.bookdto;

import lombok.Data;

@Data
public class BookResponseDtoWithoutCategories {
    private Long id;
    private String author;
    private String title;
}
