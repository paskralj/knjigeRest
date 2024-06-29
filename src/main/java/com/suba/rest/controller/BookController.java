package com.suba.rest.controller;

import com.suba.rest.repository.Book;
import com.suba.rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for handling web requests related to Book entities.
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Displays the homepage with a list of all books and a form to add new books.
     *
     * @param model Model object to add attributes for rendering the view.
     * @return String representing the view name ("homepage").
     */
    @GetMapping("/home")
    public String homepage(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("newBook", new Book());
        return "homepage";
    }

    /**
     * Processes a form submission to add a new Book entity.
     *
     * @param newBook The new Book entity to add.
     * @return String representing a redirect to the homepage ("/home") after adding the book.
     */
    @PostMapping("/newbook")
    public String addBook(@ModelAttribute("newBook") Book newBook) {
        bookService.addBook(newBook);
        return "redirect:/home";
    }
}
