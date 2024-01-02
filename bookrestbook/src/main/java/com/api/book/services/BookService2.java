package com.api.book.services;

import com.api.book.dao2.SecondBookRepository;
import com.api.book.entities.Book2;
import com.api.book.dao2.SecondBookRepository;
import com.api.book.entities.Book2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("secondDatabase")
public class BookService2 {

    @Autowired
    private SecondBookRepository bookList;

    public Book2 addBook(Book2 b){
        Book2 result=bookList.save(b);
        return result;

    }
}
