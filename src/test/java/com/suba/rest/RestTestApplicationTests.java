package com.suba.rest;

import com.suba.rest.controller.BookController;
import com.suba.rest.repository.Book;
import com.suba.rest.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for {@link BookController}.
 * This class verifies the functionality of the BookController endpoints without connecting to a real database.
 */
@WebMvcTest(BookController.class)
class BookControllerMockIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    /**
     * Test to ensure the application context loads successfully and {@link MockMvc} is injected.
     */
    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
    }

    /**
     * Test the GET /home endpoint.
     * This test verifies that the homepage view is returned with the correct model attributes.
     *
     * @throws Exception if an error occurs while performing the request.
     */
    @Test
    void testHomepage() throws Exception {
        List<Book> books = List.of(
                new Book("Book 1", "Author 1", "1234567890", 2020),
                new Book("Book 2", "Author 2", "0987654321", 2019)
        );
        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(get("/home"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("books", books))
                .andExpect(view().name("homepage"))
                .andExpect(model().attributeExists("newBook"));
    }

    /**
     * Test the POST /newbook endpoint.
     * This test verifies that a new book is added and the user is redirected to the homepage.
     *
     * @throws Exception if an error occurs while performing the request.
     */
    @Test
    void testAddBook() throws Exception {
        Book book = new Book("Book 1", "Author 1", "1234567890", 2020);

        mockMvc.perform(post("/newbook").flashAttr("newBook", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));

        // then
        verify(bookService, times(1)).addBook(book);
    }
}
