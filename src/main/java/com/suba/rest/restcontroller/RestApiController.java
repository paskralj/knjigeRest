package com.suba.rest.restcontroller;

import com.suba.rest.repository.Book;
import com.suba.rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for handling CRUD operations on Book entities.
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    private BookService bookService;

    /**
     * Retrieves a single Book entity by its ID.
     *
     * @param id The ID of the Book to retrieve.
     * @return ResponseEntity containing the Book entity and HttpStatus OK if found.
     * @throws ResourceNotFoundException if no Book with the given ID exists.
     */
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.findBookById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else throw new ResourceNotFoundException("Book not found");
    }

    /**
     * Retrieves all Book entities.
     *
     * @return ResponseEntity containing a list of Book entities and HttpStatus OK.
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Adds a new Book entity.
     *
     * @param newBook The Book entity to add.
     * @return ResponseEntity with HttpStatus CREATED if the Book is successfully added.
     */
    @PostMapping("/books")
    public ResponseEntity<HttpStatus> addBook(@RequestBody Book newBook) {
        bookService.addBook(newBook);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Updates an existing Book entity.
     *
     * @param id          The ID of the Book to update.
     * @param updatedBook The updated Book entity.
     * @return ResponseEntity with HttpStatus OK if the update is successful.
     * @throws ResourceNotFoundException if no Book with the given ID exists.
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<HttpStatus> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> optionalBook = bookService.findBookById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublishedYear(updatedBook.getPublishedYear());
            bookService.addBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new ResourceNotFoundException("Book not found");
    }

    /**
     * Deletes a Book entity by its ID.
     *
     * @param id The ID of the Book to delete.
     * @return ResponseEntity with HttpStatus NO_CONTENT if the deletion is successful.
     * @throws ResourceNotFoundException if no Book with the given ID exists.
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.findBookById(id);
        if (optionalBook.isPresent()) {
            bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else throw new ResourceNotFoundException("Book not found");
    }

}
