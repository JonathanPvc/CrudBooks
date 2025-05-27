package com.example.demo.controllers;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.example.demo.entities.Book;
import com.example.demo.services.BookServiceImpl;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/book")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity <Book> saveBook(@RequestBody Book book){
        try {
            Book saveBook = bookServiceImpl.saveBook(book);
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity <Book> updateBook(@RequestBody Book book){
        try {
            Book saveBook = bookServiceImpl.updateBook(book);
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity <List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookServiceImpl.getBooks(),HttpStatus.OK);

}
}






