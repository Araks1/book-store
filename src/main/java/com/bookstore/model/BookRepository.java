package com.bookstore.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookRepository extends CrudRepository<Book,Integer> {
    List<Book> findByAuthor(String author);

}
