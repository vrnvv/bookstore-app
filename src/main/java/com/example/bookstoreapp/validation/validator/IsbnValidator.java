package com.example.bookstoreapp.validation.validator;

import com.example.bookstoreapp.validation.Isbn;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    private static final String PATTERN_FOR_ISBN = "^(?=(?:\\D*\\d){13}$)\\d{1,5}"
            + "[-\\s]?\\d{1,7}[-\\s]?\\d{1,7}[-\\s]?\\d{1,7}[-\\s]?([\\d|X])$";

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        return isbn != null && Pattern.compile(PATTERN_FOR_ISBN).matcher(isbn).matches();
    }
}
