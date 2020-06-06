package com.gp.library;

import com.gp.library.controller.BooksController;
import com.gp.library.model.dto.BooksDto;
import com.gp.library.model.dto.LibrariesDto;
import com.gp.library.service.BooksService;
import com.gp.library.service.LibrariesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksService bookService;

    @MockBean
    private LibrariesService librariesService;

    @Test
    public void testingGetAllService() throws Exception{
        List<BooksDto> booksDtoList = new ArrayList<BooksDto>();
        when(bookService.getAllBooks()).thenReturn(booksDtoList);

        this.mockMvc.perform(get("/books/all")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testingGetAllLibraries() throws Exception{
        List<LibrariesDto> librariesDtoList = new ArrayList<LibrariesDto>();
        when(librariesService.getAllLibraries()).thenReturn(librariesDtoList);

        this.mockMvc.perform(get("/libraries/all")).andDo(print()).andExpect(status().isOk());
    }
}
