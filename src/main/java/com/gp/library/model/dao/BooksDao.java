package com.gp.library.model.dao;

import com.gp.library.model.dto.BooksDto;

import javax.persistence.*;

@Entity
@Table(name="gp_books")
public class BooksDao {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "book_id", nullable = false, unique = true)
    private int bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "author")
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="library_id")
    private LibrariesDao library;

    public BooksDao() {
    }

    public BooksDao(BooksDto booksDto) {
        this.bookId = booksDto.getBookId();
        this.bookName = booksDto.getBookName();
        this.author = booksDto.getAuthor();
        this.library = new LibrariesDao(booksDto.getLibraryId());
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LibrariesDao getLibrary() {
        return library;
    }

    public void setLibrary(LibrariesDao library) {
        this.library = library;
    }
}
