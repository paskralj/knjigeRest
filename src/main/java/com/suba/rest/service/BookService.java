package com.suba.rest.service;

import com.suba.rest.exceptions.BookNotFoundException;
import com.suba.rest.repository.Book;
import com.suba.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for performing business logic operations on Book entities.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Retrieves all books from the database.
     *
     * @return List of all books.
     */
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its ID from the database.
     *
     * @param id The ID of the book to retrieve.
     * @return Optional containing the found book, or empty if not found.
     */
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Adds a new book to the database or updates an existing one.
     *
     * @param book The book to add or update.
     */
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Deletes a book from the database by its ID.
     *
     * @param id The ID of the book to delete.
     * @throws BookNotFoundException if the book with the given ID does not exist.
     */
    public void deleteBookById(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else throw new BookNotFoundException("Book with id: " + id + " not found!");
    }

}
