package com.example.demo.controllers;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.example.demo.entities.Book;
import com.example.demo.services.BookService;
import com.example.demo.services.BookServiceImpl;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity <Book> saveBook(@RequestBody Book book){
        try {
            Book saveBook = bookService.saveBook(book);
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity <Book> updateBook(@RequestBody Book book){
        try {
            Book saveBook = bookService.updateBook(book);
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity <List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getBooks(),HttpStatus.OK);

}
    @GetMapping("/{id}")
    public ResponseEntity <Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value , HttpStatus.OK)).orElseGet(() ->
            new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Book> deleteBook(@PathVariable Long id){
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()){
            bookService.deleteBook(book.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);

        }else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}






