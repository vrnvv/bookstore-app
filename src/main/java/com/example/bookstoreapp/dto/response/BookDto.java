package com.example.bookstoreapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {
    private Long id;
    private String author;
    private String title;
}
