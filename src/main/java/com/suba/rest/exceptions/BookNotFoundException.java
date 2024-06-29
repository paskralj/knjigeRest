package com.suba.rest.exceptions;

/**
 * Exception thrown when a requested Book entity is not found.
 */
public class BookNotFoundException extends RuntimeException{

    /**
     * Constructs a new BookNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public BookNotFoundException(String message){
        super(message);
    }
}
