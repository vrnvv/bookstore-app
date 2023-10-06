package com.example.bookstoreapp.dto.categorydto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CategoryRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
}
