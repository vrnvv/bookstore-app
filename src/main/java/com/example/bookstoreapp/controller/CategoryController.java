package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.bookdto.BookResponseDtoWithoutCategories;
import com.example.bookstoreapp.dto.categorydto.CategoryRequestDto;
import com.example.bookstoreapp.dto.categorydto.CategoryResponseDto;
import com.example.bookstoreapp.service.BookService;
import com.example.bookstoreapp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
@Tag(name = "Category management", description = "Endpoints for managing categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryResponseDto>> getAll(
            @PageableDefault(size = 25, page = 0) Pageable pageable) {
        List<CategoryResponseDto> categories = categoryService.findAll(pageable);
        return ResponseEntity.ok(categories);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Create category")
    public ResponseEntity<CategoryResponseDto> createCategory(
            @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto createdCategory = categoryService.save(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category", description = "Get category by id")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        CategoryResponseDto category = categoryService.get(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Get book by category", description = "Get book by category")
    public ResponseEntity<List<BookResponseDtoWithoutCategories>> getBooksByCategoryId(
            @PathVariable Long id) {
        List<BookResponseDtoWithoutCategories> books = bookService.findAllByCategoryId(id);
        if (books != null) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update category", description = "Update category by id")
    public ResponseEntity<CategoryResponseDto> updateBook(
            @PathVariable Long id,
            @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto category = categoryService.update(id, categoryRequestDto);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Delete category by id")
    public void deleteBook(@PathVariable Long id) {
        categoryService.delete(id);
    }

}
