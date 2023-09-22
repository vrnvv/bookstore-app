package com.example.bookstoreapp.dto.request;

import com.example.bookstoreapp.validation.Isbn;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class BookRequestDto {
    @NotNull
    private String title;
    @NotEmpty
    private String author;
    @Isbn
    private String isbn;
    @NotNull
    private BigDecimal price;
    private String description;
    private String coverImage;
}
