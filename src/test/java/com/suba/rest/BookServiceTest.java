package com.suba.rest;

import com.suba.rest.repository.Book;
import com.suba.rest.repository.BookRepository;
import com.suba.rest.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link BookService}.
 */
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    /**
     * Test case for {@link BookService#findAllBooks()}.
     * Verifies that all books are retrieved correctly.
     */
    @Test
    void testFindAllBooks() {
        List<Book> books = List.of(
                new Book("Book 1", "Author 1", "1234567890", 2020),
                new Book("Book 2", "Author 2", "0987654321", 2019)
        );
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> foundBooks = bookService.findAllBooks();
        assertEquals(2, foundBooks.size());
        assertEquals("Author 2", foundBooks.get(1).getAuthor());
        assertEquals("Book 1", foundBooks.get(0).getTitle());
        assertEquals("1234567890", foundBooks.get(0).getIsbn());
        assertEquals(2019, foundBooks.get(1).getPublishedYear());
    }

    /**
     * Test case for {@link BookService#findBookById(Long)}.
     * Verifies the retrieval of books by their ID.
     */
    @Test
    void testFindBookById() {
        List<Book> books = List.of(
                new Book("Book 1", "Author 1", "1234567890", 2020),
                new Book("Book 2", "Author 2", "0987654321", 2019)
        );
        books.get(0).setId(1L);
        books.get(1).setId(2L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(books.get(0)));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(books.get(1)));

        Optional<Book> optionalFoundBookOne = bookService.findBookById(1L);
        if (optionalFoundBookOne.isPresent()) {
            Book foundBookOne = optionalFoundBookOne.get();
            assertEquals("Book 1", foundBookOne.getTitle());
            assertEquals("Author 1", foundBookOne.getAuthor());
            assertEquals("1234567890", foundBookOne.getIsbn());
            assertEquals(2020, foundBookOne.getPublishedYear());
        }

        Optional<Book> optionalFoundBookTwo = bookService.findBookById(2L);
        if (optionalFoundBookTwo.isPresent()) {
            Book foundBookTwo = optionalFoundBookTwo.get();
            assertEquals("Book 2", foundBookTwo.getTitle());
            assertEquals("Author 2", foundBookTwo.getAuthor());
            assertEquals("0987654321", foundBookTwo.getIsbn());
            assertEquals(2019, foundBookTwo.getPublishedYear());
        }

        Optional<Book> optionalNotFound = bookService.findBookById(3L);
        assertTrue(optionalNotFound.isEmpty());
    }

    /**
     * Test case for {@link BookService#addBook(Book)}.
     * Verifies that a book is added correctly.
     */
    @Test
    void testAddBook() {
        Book book = new Book("Book 1", "Author 1", "1234567890", 2020);

        // when
        bookService.addBook(book);

        // then
        verify(bookRepository, times(1)).save(book);
    }

    /**
     * Test case for {@link BookService#deleteBookById(Long)}.
     * Verifies that a book is deleted by its ID.
     */
    @Test
    void testDeleteBookById() {
        Book book = new Book("Book 1", "Author 1", "1234567890", 2020);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // when
        bookService.deleteBookById(1L);

        // then
        verify(bookRepository, times(1)).findById(1L);
    }
}
