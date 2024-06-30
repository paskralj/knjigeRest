package com.suba.rest.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "restdb")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String isbn; // ISBN (International Standard Book Number) je jedinstveni numerički identifikator za knjige
    @Column(name = "publishedYear")
    private int publishedYear;

    public Book() {
    }

    public Book(String title, String author, String isbn, int publishedYear){
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.publishedYear=publishedYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }
}
