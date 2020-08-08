package com.gp.library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Integer id) {
        super(String.format("Book with id:%d not found!", id));
    }
}
