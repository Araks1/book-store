package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/book/{author}", method = RequestMethod.GET)
    public List<Book> findBooksByAuthor(@PathVariable("author") String name) {
        List<Book> book = bookRepository.findByAuthor(name);
        return book;
    }

    @RequestMapping(value = "/book/add")
    Book addBook(String title, String author) {
        Book book = new Book(title, author);
        bookRepository.save(book);
        return book;
    }

    @RequestMapping(value = "/book/update", method = RequestMethod.PUT)
    Book updateBook(int id, String title, String author) {
        try {
            Book book = bookRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            book.setAuthor(author);
            book.setTitle(title);
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    void deleteBook(@PathVariable("id") int id) {
        bookRepository.deleteById(id);

    }
}