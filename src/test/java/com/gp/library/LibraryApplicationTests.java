package com.gp.library;

import com.gp.library.controller.BooksController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	private BooksController booksController;

	@Test
	void contextLoads() throws Exception{
		assertThat(booksController).isNotNull();
	}

}
