package com.gp.library.service;

import com.gp.library.model.dao.BooksDao;
import com.gp.library.model.dao.LibrariesDao;
import com.gp.library.model.dto.BooksDto;
import com.gp.library.model.dto.LibrariesDto;
import com.gp.library.repository.BooksRepository;
import com.gp.library.repository.LibrariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibrariesServiceImpl implements LibrariesService {

    @Autowired
    LibrariesRepository librariesRepository;

    @Autowired
    BooksRepository booksRepository;

    @Override
    public List<LibrariesDto> getAllLibraries() {
        List<LibrariesDao> librariesDaoList = this.librariesRepository.findAll();
        return librariesDaoList.stream().map(LibrariesDto::new).collect(Collectors.toList());
    }

    @Override
    public LibrariesDto getLibraryById(Integer id) {
        LibrariesDto librariesDto = null;
        Optional<LibrariesDao> librariesDao = this.librariesRepository.findById(id);
        if(librariesDao.isPresent()){
           librariesDto = new LibrariesDto(librariesDao.get());
        }
        return  librariesDto;
    }

    @Override
    public LibrariesDao saveLibrary(LibrariesDto librariesDto) {
        LibrariesDao librariesDaoResult = null;
        try {
            LibrariesDao librariesDao = new LibrariesDao(librariesDto);
            librariesDaoResult = this.librariesRepository.save(librariesDao);
        }catch (Exception e){
            System.out.print("Exception::"+e.getStackTrace());
        }
        return librariesDaoResult;
    }

    @Override
    public List<BooksDto> getBooksByLibraryId(Integer id) {
        List<BooksDao> booksDaoList = this.booksRepository.getBooksByLibraryId(id);
        return booksDaoList.stream().map(BooksDto::new).collect(Collectors.toList());
    }
}
