package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.dto.categorydto.CategoryRequestDto;
import com.example.bookstoreapp.dto.categorydto.CategoryResponseDto;
import com.example.bookstoreapp.model.entity.Category;
import com.example.bookstoreapp.repository.CategoryRepository;
import com.example.bookstoreapp.service.AbstractService;
import com.example.bookstoreapp.service.CategoryService;
import com.example.bookstoreapp.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends AbstractService<Category,
        CategoryRequestDto, CategoryResponseDto,
        CategoryMapper> implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    protected JpaRepository<Category, Long> getRepository() {
        return repository;
    }

    protected CategoryMapper getMapper() {
        return mapper;
    }
}
