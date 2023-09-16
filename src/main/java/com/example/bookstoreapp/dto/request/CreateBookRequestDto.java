package com.example.bookstoreapp.dto.request;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CreateBookRequestDto {
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
