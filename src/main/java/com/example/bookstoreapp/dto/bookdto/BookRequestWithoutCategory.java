package com.example.bookstoreapp.dto.bookdto;

import com.example.bookstoreapp.validation.Isbn;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class BookRequestWithoutCategory {
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
