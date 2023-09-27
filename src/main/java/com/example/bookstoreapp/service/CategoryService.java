package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.categorydto.CategoryRequestDto;
import com.example.bookstoreapp.dto.categorydto.CategoryResponseDto;

public interface CategoryService extends
        BasicService<CategoryRequestDto, CategoryResponseDto, Long> {

}
