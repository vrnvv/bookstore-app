package com.example.bookstoreapp.dto.bookdto;

import java.util.List;
import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String author;
    private String title;
    private List<Long> categoryId;
}
