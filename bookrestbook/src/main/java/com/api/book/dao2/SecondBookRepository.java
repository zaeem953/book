package com.api.book.dao2;

import com.api.book.entities.Book2;
import com.api.book.entities.Book2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("secondDatabase")
public interface SecondBookRepository extends CrudRepository<Book2,Integer> {
    public Book2 findById(int id);
}
