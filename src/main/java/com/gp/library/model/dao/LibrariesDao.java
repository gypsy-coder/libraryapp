package com.gp.library.model.dao;

import com.gp.library.model.dto.LibrariesDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="gp_library")
public class LibrariesDao {

    @Id
    @Column(name = "library_id", nullable = false, unique = true)
    @SequenceGenerator(name="library_seq_gen", sequenceName = "library_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "library_seq_gen")
    private int libraryId;

    @Column(name = "library_name")
    private String libraryName;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    private List<BooksDao> books;

    public LibrariesDao() {
        this.libraryName = "DEFAULT_LIBRARY";
        this.location = "DEFAULT_LOCATION";
        this.books = null;
    }

    public LibrariesDao(LibrariesDto librariesDto) {
        this.libraryName = librariesDto.getLibraryName();
        this.location = librariesDto.getLocation();
    }

    public LibrariesDao(int libraryId) {
        this.libraryId = libraryId;
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

    public List<BooksDao> getBooks() {
        return books;
    }

    public void setBooks(List<BooksDao> books) {
        this.books = books;
    }
}
