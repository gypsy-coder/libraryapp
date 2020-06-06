package com.gp.library;

import com.gp.library.controller.BooksController;
import com.gp.library.model.dto.BooksDto;
import com.gp.library.service.BooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BooksController.class)
public class ApiDocumentationJUnit5IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksService bookService;

    BooksDto validBook;

    @BeforeEach
    public void setUp() {
        validBook = BooksDto.Builder.builder().setBookId(1)
                .setBookName("Alchemist")
                .setAuthor("Paulo Coelho")
                .setLibraryId(1)
                .build();
    }

    @Test
    public void getBook() throws Exception {

        given(bookService.getBookById(1)).willReturn(validBook);

        ConstrainedFields fields = new ConstrainedFields(BooksDto.class);

        mockMvc.perform(get("/books/bookid/{id}", validBook.getBookId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId", is(validBook.getBookId())))
                .andExpect(jsonPath("$.bookName", is("Alchemist")))
                .andDo(document("v1/book-get",
                        pathParameters(
                                parameterWithName("id").description("Book Id of desired book to get.")
                        ),
                        responseFields(
                                fields.withPath("bookId").description("Id of Book").type(Integer.class),
                                fields.withPath("bookName").description("Name of Book").type(String.class),
                                fields.withPath("author").description("Author of Book").type(String.class),
                                fields.withPath("libraryId").description("Id of Library")
                        )));
    }

    private static class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}
