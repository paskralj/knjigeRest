package com.suba.rest.restcontroller;

import com.suba.rest.repository.Book;
import com.suba.rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/{id}")
    public Optional<Book> getBook(@PathVariable Long id){
        return bookService.findBookById(id);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();
    }
}
