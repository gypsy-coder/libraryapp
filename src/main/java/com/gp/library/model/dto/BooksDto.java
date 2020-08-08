package com.gp.library.model.dto;

import com.gp.library.model.dao.BooksDao;

import javax.validation.constraints.NotNull;

public class BooksDto {

    private int bookId;

    @NotNull(message = "Book name mandatory!")
    private String bookName;

    private String author;

    private int libraryId;

    public BooksDto(){}

    public BooksDto(BooksDao booksDao){
        this.bookId = booksDao.getBookId();
        this.bookName = booksDao.getBookName();
        this.author = booksDao.getAuthor();
        this.libraryId = booksDao.getLibrary().getLibraryId();
    }

    public BooksDto(Builder builder){
        this.bookId = builder.bookId;
        this.bookName = builder.bookName;
        this.author = builder.author;
        this.libraryId = builder.libraryId;
    }

    public static class Builder{

        private int bookId;

        private String bookName;

        private String author;

        private int libraryId;

        public static Builder builder(){
            return new Builder();
        }

        private Builder(){}

        public Builder setBookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder setBookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setLibraryId(int libraryId) {
            this.libraryId = libraryId;
            return this;
        }

        public BooksDto build(){
            return new BooksDto(this);
        }
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

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    @Override
    public String toString() {
        return "BooksDto{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", libraryId='" + libraryId + '\'' +
                '}';
    }
}
