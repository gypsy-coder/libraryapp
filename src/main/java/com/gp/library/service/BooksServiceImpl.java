package com.gp.library.service;

import com.gp.library.model.dao.BooksDao;
import com.gp.library.model.dao.LibrariesDao;
import com.gp.library.model.dto.BooksDto;
import com.gp.library.model.dto.LibrariesDto;
import com.gp.library.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService{

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    LibrariesService librariesService;

    @Override
    public List<BooksDto> getAllBooks() {
        List<BooksDao> booksDaoList = this.booksRepository.findAll();
        return booksDaoList.stream().map(BooksDto::new).collect(Collectors.toList());
    }

    @Override
    public BooksDto getBookById(Integer id) {
        return new BooksDto(this.booksRepository.findById(id).get());
    }

    @Override
    public void saveBook(BooksDto booksDto) {
        try{
            LibrariesDto librariesDto = this.librariesService.getLibraryById(booksDto.getLibraryId());
            if(librariesDto == null){
                List<LibrariesDto> librariesDtoList = this.librariesService.getAllLibraries();
                if(!librariesDtoList.isEmpty()){
                    booksDto.setLibraryId(librariesDtoList.get(0).getLibraryId());
                }else{
                    LibrariesDao librariesDao = this.librariesService.saveLibrary(new LibrariesDto());
                    booksDto.setLibraryId(librariesDao.getLibraryId());
                }
            }else{
                booksDto.setLibraryId(librariesDto.getLibraryId());
            }
            BooksDao booksDao = new BooksDao(booksDto);
            this.booksRepository.save(booksDao);
        }catch (Exception e){
            System.out.print("Lib Exception::"+e.getMessage());
        }

    }

    @Override
    public void updateBook(Integer id, BooksDto booksDto) {
        if(id == booksDto.getLibraryId()) {
            Optional<BooksDao> booksDao = this.booksRepository.findById(id);
            if(booksDao.isPresent()){
                this.booksRepository.delete(booksDao.get());
                this.booksRepository.save(new BooksDao(booksDto));
            }
        }
    }

    @Override
    public List<BooksDto> getBooksByLibraryId(Integer id) {
        List<BooksDao> booksDaoList = this.booksRepository.getBooksByLibraryId(id);
        return booksDaoList.stream().map(BooksDto::new).collect(Collectors.toList());
    }
}
