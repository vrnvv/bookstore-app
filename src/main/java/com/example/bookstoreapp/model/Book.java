package com.example.bookstoreapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @Column(name = "isban")
    @NonNull
    private String isbn;
    @NonNull
    private BigDecimal price;
    private String description;
    private String coverImage;

    public Book() {

    }
}

//id (Long, PK)
//title (String, not null)
//author (String, not null)
//isbn (String, not null, unique)
//price (BigDecimal, not null)
//description (String)
//coverImage (String)