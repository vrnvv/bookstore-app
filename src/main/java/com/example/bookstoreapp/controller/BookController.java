package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.request.BookRequestDto;
import com.example.bookstoreapp.dto.response.BookDto;
import com.example.bookstoreapp.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing products")
@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<BookDto>> getAll(
            @PageableDefault(size = 25, page = 0,
                    sort = {"author", "title"}) Pageable pageable) {
        List<BookDto> books = bookService.findAll(pageable);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    @Operation(summary = "Create books")
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookRequestDto bookDto) {
        BookDto createdBook = bookService.save(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book", description = "Get book by id")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto book = bookService.get(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book", description = "Update book by id")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id,
                                              @RequestBody @Valid
                                                      BookRequestDto updatedBookDto) {
        BookDto book = bookService.update(id, updatedBookDto);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book", description = "Delete book by id")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search book",
            description = "search book by author, title and isbn")
    public ResponseEntity<List<BookDto>> searchBooks(
            @RequestParam String search,
            @PageableDefault(size = 25, page = 0,
                    sort = {"author", "title"}) Pageable pageable) {
        List<BookDto> books = bookService.searchBook(search, pageable);
        return ResponseEntity.ok(books);
    }
}
