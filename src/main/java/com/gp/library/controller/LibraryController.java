package com.gp.library.controller;

import com.gp.library.model.dto.BooksDto;
import com.gp.library.model.dto.LibrariesDto;
import com.gp.library.service.LibrariesService;
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
@RequestMapping(value = "libraries")
public class LibraryController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LibrariesService librariessService;

    @RequestMapping(value="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LibrariesDto>> getAllLibraries(){
        try{
            List<LibrariesDto> librariesDtoList = this.librariessService.getAllLibraries();
            return new ResponseEntity(librariesDtoList, HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @RequestMapping(value = "/libraryid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LibrariesDto> getLibraryById(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<LibrariesDto>(this.librariessService.getLibraryById(id),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveLibrary(@RequestBody LibrariesDto librariesDto){
        try{
            this.librariessService.saveLibrary(librariesDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/libraryid/{id}/books/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BooksDto>> getBooksByLibraryId(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<List<BooksDto>>(this.librariessService.getBooksByLibraryId(id),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
