package com.BookStore.BookStore.exceptions;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BookNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
