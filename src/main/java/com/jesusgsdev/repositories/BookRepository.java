package com.jesusgsdev.repositories;

import com.jesusgsdev.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    List<Book> findBooksByTitleContaining(String title);

    List<Book> findBookByAuthor(String author);

    Optional<Book> findBookByIsbn(String isbn);


}
