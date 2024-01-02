package com.api.book.controllers;

import com.api.book.entities.Book;
import com.api.book.entities.Book2;
import com.api.book.services.BookService;
import com.api.book.services.BookService2;
import com.api.book.entities.Book;
import com.api.book.entities.Book2;
import com.api.book.services.BookService;
import com.api.book.services.BookService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController

public class BookController {

    @Autowired
    @Qualifier("secondDatabase")
    private BookService2 bookService2;

    @Autowired
    @Qualifier("firstDatabase")
    private BookService bookService;

//    @ResponseBody
//    @RequestMapping(value = "/book",method = RequestMethod.GET)
    @GetMapping("/book")
    public List<Book> getBooks(){
        return this.bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public Book getBookId(@PathVariable("id") int id){
        return bookService.getBookById(id);
    }

    @PostMapping("/book")
    public Book2 addBook(@RequestBody Book2 book){
        Book2 b=bookService2.addBook(book);
        return b;
    }

    @DeleteMapping("/book/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId){
        bookService.deleteBook(bookId);
    }

}
