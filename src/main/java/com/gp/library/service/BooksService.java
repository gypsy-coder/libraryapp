package com.gp.library.service;

import com.gp.library.model.dto.BooksDto;

import java.util.List;

public interface BooksService {

    List<BooksDto> getAllBooks();

    BooksDto getBookById(Integer id);

    void saveBook(BooksDto booksDto);

    void updateBook(Integer id, BooksDto booksDto);

    List<BooksDto> getBooksByLibraryId(Integer id);
}
