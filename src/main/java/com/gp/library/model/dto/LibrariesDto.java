package com.gp.library.model.dto;

import com.gp.library.model.dao.LibrariesDao;
import java.util.List;
import java.util.stream.Collectors;

public class LibrariesDto {

    private int libraryId;

    private String libraryName;

    private String location;

    private List<BooksDto> books;

    public LibrariesDto() {
        this.libraryName = "DEFAULT_LIBRARY";
        this.location = "DEFAULT_LOCATION";
        this.books = null;
    }

    public LibrariesDto(LibrariesDao librariesDao){
        this.libraryId = librariesDao.getLibraryId();
        this.libraryName = librariesDao.getLibraryName();
        this.location = librariesDao.getLocation();
        this.books = librariesDao.getBooks().stream().map(BooksDto::new).collect(Collectors.toList());
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<BooksDto> getBooks() {
        return books;
    }

    public void setBooks(List<BooksDto> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "LibrariesDto{" +
                "libraryId='" + libraryId + '\'' +
                ", libraryName='" + libraryName + '\'' +
                ", location='" + location + '\'' +
                ", books=" + books +
                '}';
    }
}
