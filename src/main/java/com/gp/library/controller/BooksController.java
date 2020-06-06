package com.gp.library.controller;

import com.gp.library.model.dto.BooksDto;
import com.gp.library.service.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "books")
public class BooksController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BooksService booksService;

    @RequestMapping(value="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BooksDto>> getAllBooks(){
        try{
            List<BooksDto> booksDtoList = this.booksService.getAllBooks();
            return new ResponseEntity(booksDtoList, HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @RequestMapping(value = "/libraryid/{id}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BooksDto>> getBooksByLibraryId(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<List<BooksDto>>(this.booksService.getBooksByLibraryId(id),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/bookid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksDto> getBookById(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<BooksDto>(this.booksService.getBookById(id),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveBook(@RequestBody BooksDto booksDto){
        try{
            this.booksService.saveBook(booksDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBook(@PathVariable("id") Integer id,@RequestBody BooksDto booksDto){
        try{
            this.booksService.updateBook(id, booksDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

