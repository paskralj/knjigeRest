package com.suba.rest.service;

import com.suba.rest.exceptions.BookNotFoundException;
import com.suba.rest.repository.Book;
import com.suba.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBookById(Long id){
        if (bookRepository.findById(id).isPresent()){
            bookRepository.deleteById(id);
        }
        else throw new BookNotFoundException("Book with id: " + id + " not found!");
    }

}
