package com.suba.rest.controller;

import com.suba.rest.repository.Book;
import com.suba.rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/home")
    public String homepage(Model model){
        model.addAttribute("books",bookService.findAllBooks());
        model.addAttribute("newBook", new Book());
        return "homepage";
    }

    @PostMapping("/newbook")
    public String addBook(@ModelAttribute("newBook") Book newBook){
        bookService.addBook(newBook);
        return "redirect:/home";
    }
}
