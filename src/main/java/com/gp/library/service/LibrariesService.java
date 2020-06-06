package com.gp.library.service;

import com.gp.library.model.dao.LibrariesDao;
import com.gp.library.model.dto.BooksDto;
import com.gp.library.model.dto.LibrariesDto;

import java.util.List;

public interface LibrariesService {

    public List<LibrariesDto> getAllLibraries();

    LibrariesDto getLibraryById(Integer id);

    LibrariesDao saveLibrary(LibrariesDto librariesDto);

    List<BooksDto> getBooksByLibraryId(Integer id);
}
