package com.example.bookstoreapp;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreAppApplication {
    private BookService bookService;

    @Autowired
    public BookstoreAppApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookstoreAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setAuthor("Lesya Ukrainka");
            book.setTitle("Forest Song");
            book.setPrice(BigDecimal.valueOf(100));
            book.setIsbn("9785457276406");

            bookService.save(book);
            System.out.println(bookService.findAll());
        };

    }
}
